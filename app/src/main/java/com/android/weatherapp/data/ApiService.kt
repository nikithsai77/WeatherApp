package com.android.weatherapp.data

import com.android.weatherapp.domain.WeatherDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather?")
    suspend fun getItems(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") apiKey: String = "b1b15e88fa797225412429c1c50c122a1"): WeatherDetails

    @GET("weather?")
    suspend fun getByCityStateCode(@Query("q=") lat: String, @Query("appid") apiKey: String = "b1b15e88fa797225412429c1c50c122a1"): WeatherDetails
}