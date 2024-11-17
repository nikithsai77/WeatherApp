package com.android.weatherapp.data

import com.android.weatherapp.domain.ApiRepository
import com.android.weatherapp.domain.WeatherDetails

class ApiRepositoryImpl(private val apiService: ApiService) : ApiRepository {

    override suspend fun getItems(lat: String, lon: String): WeatherDetails {
        return apiService.getItems(lat, lon)
    }

    override suspend fun getByCityStateCode(cityAndState: String): WeatherDetails {
        return apiService.getByCityStateCode(cityAndState)
    }

}