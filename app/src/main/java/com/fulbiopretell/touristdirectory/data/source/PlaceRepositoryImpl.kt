package com.fulbiopretell.touristdirectory.data.source

import android.util.Log
import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.di.LocalDataSource
import com.fulbiopretell.touristdirectory.di.RemoteDataSource
import com.fulbiopretell.touristdirectory.domain.repository.PlaceRepository
import com.fulbiopretell.touristdirectory.util.Result

class PlaceRepositoryImpl(
    @LocalDataSource private val localDataSource: PlaceDataSource,
    @RemoteDataSource private val remoteDataSource: PlaceDataSource
) : PlaceRepository {
    override suspend fun getPlaces(): Result<List<Place>> {
        return try {
            val remoteResult = remoteDataSource.getPlaces()
            Log.e("PlaceRepositoryImpl", "getPlaces: $remoteResult")
            if (remoteResult is Result.Success) {
                localDataSource.savePlaces(remoteResult.data)
            }
            remoteResult
        } catch (e: Exception) {
            val localResult = localDataSource.getPlaces()
            if (localResult is Result.Success) {
                localResult
            } else {
                Log.e("PlaceRepositoryImpl", "getPlaces: ${e.message}")
                Result.Error(Exception("No data available: ${e.message}"))
            }
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
}
