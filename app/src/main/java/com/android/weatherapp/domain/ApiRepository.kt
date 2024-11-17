package com.android.weatherapp.domain

interface ApiRepository {
    suspend fun getItems(lat: String, lon: String): WeatherDetails
    suspend fun getByCityStateCode(cityAndState: String): WeatherDetails
}