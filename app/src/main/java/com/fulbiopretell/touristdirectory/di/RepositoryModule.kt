package com.fulbiopretell.touristdirectory.di

import android.content.Context
import com.fulbiopretell.touristdirectory.data.source.PlaceDataSource
import com.fulbiopretell.touristdirectory.data.source.PlaceRepositoryImpl
import com.fulbiopretell.touristdirectory.domain.repository.PlaceRepository
import com.fulbiopretell.touristdirectory.domain.usecase.GetPlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideTouristRepository(
        context: Context,
        @LocalDataSource localDataSource: PlaceDataSource,
        @RemoteDataSource remoteDataSource: PlaceDataSource
    ): PlaceRepository {
        return PlaceRepositoryImpl(context,localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetPlacesUseCase(placeRepository: PlaceRepository): GetPlacesUseCase {
        return GetPlacesUseCase(placeRepository)
    }
}
