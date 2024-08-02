package com.fulbiopretell.touristdirectory.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val address: String,
    val imageBase64: String?
)