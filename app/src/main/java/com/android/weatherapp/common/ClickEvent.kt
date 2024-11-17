package com.android.weatherapp.common

sealed class ClickEvent {
    object GetTheLocation: ClickEvent()
    data class GetCityAndState(val cityAndState: String): ClickEvent()
}
