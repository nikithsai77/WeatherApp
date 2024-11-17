package com.android.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weatherapp.common.ClickEvent
import com.android.weatherapp.common.Resource
import com.android.weatherapp.domain.ApiUseCase
import com.android.weatherapp.domain.CityAndStateUseCase
import com.android.weatherapp.domain.LocationClient
import com.android.weatherapp.domain.WeatherDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val locationClient: LocationClient, private val apiUseCase: ApiUseCase, private val cityAndStateUseCase: CityAndStateUseCase) : ViewModel() {
    private val _resource = MutableStateFlow<Resource<WeatherDetails>>(Resource.Loading)
    val resource: StateFlow<Resource<WeatherDetails>> = _resource.asStateFlow()

    private fun startLocationUpdates() = viewModelScope.launch {
        locationClient.getLocationUpdates().collect { location ->
            apiUseCase(location.latitude.toString(), location.longitude.toString()).collect { result ->
                _resource.update {
                    result
                }
            }
        }
    }

    private fun cityAndState(cityAndState: String) {
        cityAndStateUseCase(cityAndState = cityAndState).onEach { result ->
            _resource.update {
                result
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(clickEvent: ClickEvent) {
        when(clickEvent) {
            is ClickEvent.GetTheLocation -> startLocationUpdates()
            is ClickEvent.GetCityAndState -> cityAndState(clickEvent.cityAndState)
        }
    }

}