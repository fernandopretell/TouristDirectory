package com.fulbiopretell.touristdirectory.domain.repository

import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.util.Result

interface PlaceRepository {
    suspend fun getPlaces(): Result<List<Place>>
    suspend fun getPlaceById(id: String): Result<PlaceDetail>?
}
