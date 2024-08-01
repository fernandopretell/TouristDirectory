package com.fulbiopretell.touristdirectory.domain.usecase

import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.domain.repository.PlaceRepository
import com.fulbiopretell.touristdirectory.util.Result
import javax.inject.Inject

class GetPlaceByIdUseCase @Inject constructor(private val placeRepository: PlaceRepository){
    suspend operator fun invoke(id: String): Result<PlaceDetail>? = placeRepository.getPlaceById(id)
}
