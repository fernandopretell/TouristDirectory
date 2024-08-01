package com.fulbiopretell.touristdirectory.data.source.remote

import com.fulbiopretell.touristdirectory.data.model.PlaceDetailsResponseTotal
import com.fulbiopretell.touristdirectory.data.model.PlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("maps/api/place/nearbysearch/json")
    suspend fun getNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): Response<PlacesResponse>

    @GET("maps/api/place/details/json")
    suspend fun getPlaceById(
        @Query("place_id") placeId: String,
        @Query("key") apiKey: String
    ): Response<PlaceDetailsResponseTotal>
}
