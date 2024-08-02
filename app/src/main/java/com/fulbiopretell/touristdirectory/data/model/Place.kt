package com.fulbiopretell.touristdirectory.data.model

data class Place(
    val id: String,
    val title: String,
    val description: String,
    var imageUrl: String,
    val imageBase64: String? = null
)
