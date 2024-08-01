package com.fulbiopretell.touristdirectory.data.source.remote

import com.fulbiopretell.touristdirectory.data.mappers.toPlace
import com.fulbiopretell.touristdirectory.data.mappers.toPlaces
import com.fulbiopretell.touristdirectory.data.mappers.toPlacesConverter
import com.fulbiopretell.touristdirectory.util.Constants
import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.data.source.PlaceDataSource
import javax.inject.Inject
import com.fulbiopretell.touristdirectory.util.Result

class RemotePlaceDataSource @Inject constructor(
    private val apiService: ApiService
) : PlaceDataSource {
    override suspend fun getPlaces(): Result<List<Place>> {
        return try {
            val response = apiService.getNearbyPlaces(
                Constants.LOCATION_LIMA,
                Constants.RADIUS,
                Constants.TOURIST_PLACE,
                Constants.GOOGLE_API_KEY
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it.results.toPlacesConverter())
                } ?: Result.Error(Exception("No data available"))
            } else {
                Result.Error(Exception("Error fetching nearby places: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPlaceById(id: String): Result<PlaceDetail> {
        return try {
            val response = apiService.getPlaceById(id, Constants.GOOGLE_API_KEY)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it.result.toPlace())
                } ?: Result.Error(Exception("No data available"))
            } else {
                Result.Error(Exception("Error fetching place by id: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun savePlaces(places: List<Place>) {
        // No se necesita implementación en RemotePlaceDataSource
        // ya que guardar lugares es una operación local
    }
}
