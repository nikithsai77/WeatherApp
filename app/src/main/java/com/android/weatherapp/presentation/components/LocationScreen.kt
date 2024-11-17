package com.android.weatherapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.weatherapp.common.Resource
import com.android.weatherapp.domain.WeatherDetails
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LocationScreen(state: StateFlow<Resource<WeatherDetails>>, onEvent: () -> Unit) {
    val value by state.collectAsStateWithLifecycle()
    when(value) {
        is Resource.Loading -> LoadingSymbol()
        is Resource.Success -> DisplayItems(value.data)
        is Resource.Error -> Retry(value.errorMessage!!, onEvent)
    }
}