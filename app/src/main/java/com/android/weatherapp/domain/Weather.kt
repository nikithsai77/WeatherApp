package com.android.weatherapp.domain

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)