package com.fulbiopretell.touristdirectory.data.source

import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.util.Result

interface PlaceDataSource {
    suspend fun getPlaces(): Result<List<Place>>
    suspend fun getPlaceById(id: String): Result<PlaceDetail>?
    suspend fun savePlaces(places: List<Place>)
}
