package com.fulbiopretell.touristdirectory.presentation.place_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fulbiopretell.touristdirectory.data.model.Place
import com.fulbiopretell.touristdirectory.domain.usecase.GetPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.fulbiopretell.touristdirectory.util.Result


@HiltViewModel
class PlaceListViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase
) : ViewModel() {

    private val _places = MutableLiveData<Result<List<Place>>>()
    val places: LiveData<Result<List<Place>>> get() = _places

    init {
        fetchNearbyPlaces()
    }

    fun fetchNearbyPlaces() {
        viewModelScope.launch {
            _places.value = Result.Loading
            Log.e("PlaceListViewModel", "fetchNearbyPlaces: ")
            val response = getPlacesUseCase()
            _places.value = response
        }
    }
}
