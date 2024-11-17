package com.android.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.weatherapp.common.ClickEvent
import com.android.weatherapp.presentation.components.CityAndState
import com.android.weatherapp.presentation.components.LocationRequest
import com.android.weatherapp.presentation.components.LocationScreen
import com.android.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    LocationRequest(getLocation = { LocationScreen(viewModel.resource) { viewModel.onEvent(ClickEvent.GetTheLocation) }
                        viewModel.onEvent(ClickEvent.GetTheLocation)
                    }) {
                        CityAndState(content = { LocationScreen(viewModel.resource) { } }, cityBlock = {
                            ClickEvent.GetCityAndState(cityAndState = it)
                        })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}