package com.fulbiopretell.touristdirectory.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fulbiopretell.touristdirectory.data.model.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}
