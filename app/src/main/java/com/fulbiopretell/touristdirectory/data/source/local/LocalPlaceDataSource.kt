package com.fulbiopretell.touristdirectory.data.source.local

import com.fulbiopretell.touristdirectory.data.mappers.toPlaceEntity
import com.fulbiopretell.touristdirectory.data.mappers.toPlaces
import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.data.source.PlaceDataSource
import com.fulbiopretell.touristdirectory.util.Result
import javax.inject.Inject

class LocalPlaceDataSource @Inject constructor(
    private val placeDao: PlaceDao
) : PlaceDataSource {
    override suspend fun getPlaces(): Result<List<Place>> {
        return try {
            val places = placeDao.getAllPlaces()
            if (places.isNotEmpty()) {
                Result.Success(places.toPlaces())
            } else {
                Result.Error(Exception("No local data available"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun savePlaces(places: List<Place>) {
        placeDao.insertAll(places.map { it.toPlaceEntity() })
    }

    override suspend fun getPlaceById(id: String): Result<PlaceDetail>? {
        //no vamos a guardar PlacesDetail en la base de datos local
        return null
    }
}

