package com.fulbiopretell.touristdirectory.data.model

data class PlacesResponse(
    val results: List<PlaceResult>
)

data class PlaceResult(
    val place_id: String,
    val name: String,
    val geometry: Geometry,
    val photos: List<Photo>?,
    val vicinity: String?
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class Photo(
    val photo_reference: String
)
