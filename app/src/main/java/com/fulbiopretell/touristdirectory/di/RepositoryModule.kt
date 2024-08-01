package com.fulbiopretell.touristdirectory.di

import com.fulbiopretell.touristdirectory.data.source.PlaceDataSource
import com.fulbiopretell.touristdirectory.data.source.PlaceRepositoryImpl
import com.fulbiopretell.touristdirectory.domain.repository.PlaceRepository
import com.fulbiopretell.touristdirectory.domain.usecase.GetPlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTouristRepository(
        @LocalDataSource localDataSource: PlaceDataSource,
        @RemoteDataSource remoteDataSource: PlaceDataSource
    ): PlaceRepository {
        return PlaceRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetPlacesUseCase(placeRepository: PlaceRepository): GetPlacesUseCase {
        return GetPlacesUseCase(placeRepository)
    }
}
