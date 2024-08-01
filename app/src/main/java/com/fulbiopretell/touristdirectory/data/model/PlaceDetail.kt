package com.fulbiopretell.touristdirectory.data.model

data class PlaceDetail(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Double,
    val userRatingsTotal: Double,
    val openingNow: Boolean,
    val reviews: List<SimpleReview>,
)

data class SimpleReview(
    val authorName: String,
    val profilePhotoUrl: String,
    val rating: Int,
    val relativeTimeDescription: String,
    val text: String
)