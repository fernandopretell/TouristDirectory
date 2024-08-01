package com.fulbiopretell.touristdirectory.presentation.place_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.data.model.PlaceDetail
import com.fulbiopretell.touristdirectory.domain.usecase.GetPlaceByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.fulbiopretell.touristdirectory.util.Result


@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceByIdUseCase: GetPlaceByIdUseCase
) : ViewModel() {

    private val _place = MutableLiveData<Result<PlaceDetail>?>()
    val place: LiveData<Result<PlaceDetail>?> get() = _place

    fun fetchPlaceById(id: String) {
        viewModelScope.launch {
            _place.value = Result.Loading
            _place.value = getPlaceByIdUseCase(id)
        }
    }
}
