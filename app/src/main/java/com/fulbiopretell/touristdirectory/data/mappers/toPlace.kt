package com.fulbiopretell.touristdirectory.data.mappers

import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.data.model.PlaceDetailResponse
import com.fulbiopretell.touristdirectory.data.model.PlaceEntity
import com.fulbiopretell.touristdirectory.data.model.PlaceResult
import com.fulbiopretell.touristdirectory.data.model.SimpleReview
import com.fulbiopretell.touristdirectory.util.Constants

//con esta funcion obtengo la url de la imagen de la api de google
fun getPhotoUrl(photoReference: String, apiKey: String, maxWidth: Int = 400): String {
    return "https://maps.googleapis.com/maps/api/place/photo?maxwidth=$maxWidth&photoreference=$photoReference&key=$apiKey"
}

fun PlaceResult.toPlace(): Place {
    val fotoReference = this.photos?.firstOrNull()?.photo_reference ?: ""
    return Place(
        id = this.place_id,
        title = this.name,
        description = this.vicinity ?: "No description available",
        imageUrl = getPhotoUrl(fotoReference, Constants.GOOGLE_API_KEY)
    )
}

fun List<PlaceResult>.toPlacesConverter(): List<Place> {
    return this.map { it.toPlace() }
}

fun PlaceDetailResponse.toPlace(): PlaceDetail {
    val fotoReference = this.photos?.firstOrNull()?.photo_reference ?: ""
    return PlaceDetail(
        id = this.place_id,
        title = this.name,
        description = this.vicinity,
        imageUrl = getPhotoUrl(fotoReference, Constants.GOOGLE_API_KEY),
        address = this.vicinity ?: "No address available",
        latitude = this.geometry.location.lat,
        longitude = this.geometry.location.lng,
        rating = this.rating,
        userRatingsTotal = this.user_ratings_total.toDouble(),
        openingNow = this.opening_hours?.open_now ?: false,
        reviews = this.reviews?.map {
            SimpleReview(
                authorName = it.author_name,
                profilePhotoUrl = it.profile_photo_url,
                rating = it.rating,
                relativeTimeDescription = it.relative_time_description,
                text = it.text
            )
        } ?: emptyList()
    )
}

fun PlaceEntity.toPlace(): Place {
    return Place(
        id = this.id,
        title = this.name,
        description = this.address,
        imageUrl = this.imageUrl
    )
}

fun List<PlaceEntity>.toPlaces(): List<Place> {
    return this.map { it.toPlace() }
}

fun Place.toPlaceEntity(): PlaceEntity {
    return PlaceEntity(
        id = this.id,
        name = this.title,
        address = this.description,
        imageUrl = this.imageUrl
    )
}

