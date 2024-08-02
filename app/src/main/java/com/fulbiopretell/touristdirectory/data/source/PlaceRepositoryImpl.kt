package com.fulbiopretell.touristdirectory.data.source

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.Glide
import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.di.LocalDataSource
import com.fulbiopretell.touristdirectory.di.RemoteDataSource
import com.fulbiopretell.touristdirectory.domain.repository.PlaceRepository
import com.fulbiopretell.touristdirectory.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import android.util.Base64
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class PlaceRepositoryImpl @Inject constructor(
    private val context: Context,
    @LocalDataSource private val localDataSource: PlaceDataSource,
    @RemoteDataSource private val remoteDataSource: PlaceDataSource
) : PlaceRepository {
    override suspend fun getPlaces(): Result<List<Place>> {
        return try {
            val remoteResult = remoteDataSource.getPlaces()
            Log.e("PlaceRepositoryImpl", "getPlaces: $remoteResult")
            if (remoteResult is Result.Success) {
                coroutineScope {
                    launch(Dispatchers.IO) {
                        val remoteCopy = remoteResult.copy()
                        val placesWithBase64 = remoteCopy.data.map { place ->
                            val base64Image = place.imageUrl.let { downloadImageAsBase64(context, it) }
                            place.copy(imageBase64 = base64Image)
                        }
                        localDataSource.savePlaces(placesWithBase64)
                    }
                }
                remoteResult
            } else {
                getLocalPlaces()
            }

        } catch (e: Exception) {
            Log.e("PlaceRepositoryImpl", "Error fetching remote places: ${e.message}")
            getLocalPlaces()
        }
    }

    private suspend fun getLocalPlaces(): Result<List<Place>> {
        return try {
            val localResult = localDataSource.getPlaces()
            if (localResult is Result.Success) {
                localResult
            } else {
                Log.e("PlaceRepositoryImpl", "Error fetching local places")
                Result.Error(Exception("No data available"))
            }
        } catch (localException: Exception) {
            Log.e("PlaceRepositoryImpl", "Error fetching local places: ${localException.message}")
            Result.Error(Exception("Unexpected error: ${localException.message}"))
        }
    }

    override suspend fun getPlaceById(id: String): Result<PlaceDetail>? {
        return try {
            val remoteResult = remoteDataSource.getPlaceById(id)
            remoteResult
        } catch (e: Exception) {
            Result.Error(Exception("No data available"))
        }
    }

    private suspend fun downloadImageAsBase64(context: Context, url: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get()
                    .let { bitmap ->
                        bitmapToBase64(bitmap)
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}
