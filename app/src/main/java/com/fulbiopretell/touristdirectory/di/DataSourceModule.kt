package com.fulbiopretell.touristdirectory.di

import com.fulbiopretell.touristdirectory.data.source.PlaceDataSource
import com.fulbiopretell.touristdirectory.data.source.local.LocalPlaceDataSource
import com.fulbiopretell.touristdirectory.data.source.local.PlaceDao
import com.fulbiopretell.touristdirectory.data.source.remote.ApiService
import com.fulbiopretell.touristdirectory.data.source.remote.RemotePlaceDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @LocalDataSource
    @Provides
    @Singleton
    fun provideLocalDataSource(
        placeDao: PlaceDao
    ): PlaceDataSource {
        return LocalPlaceDataSource(placeDao)
    }

    @RemoteDataSource
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService
    ): PlaceDataSource {
        return RemotePlaceDataSource(apiService)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource