package com.fulbiopretell.touristdirectory.di

import android.content.Context
import com.fulbiopretell.touristdirectory.BuildConfig
import com.fulbiopretell.touristdirectory.data.source.PlaceDataSource
import com.fulbiopretell.touristdirectory.data.source.local.LocalPlaceDataSource
import com.fulbiopretell.touristdirectory.data.source.local.PlaceDao
import com.fulbiopretell.touristdirectory.data.source.remote.ApiService
import com.fulbiopretell.touristdirectory.data.source.remote.RemotePlaceDataSource
import com.fulbiopretell.touristdirectory.util.KeystoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        apiService: ApiService,
        apiKey: String?
    ): PlaceDataSource {
        if (apiKey == null) {
            throw IllegalStateException("API key must not be null")
        }
        return RemotePlaceDataSource(apiService, apiKey)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource