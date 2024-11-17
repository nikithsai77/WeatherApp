@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.weatherapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.weatherapp.common.timeToDate
import com.android.weatherapp.domain.Weather
import com.android.weatherapp.domain.WeatherDetails

@Composable
fun DisplayItems(itemList: WeatherDetails?) {
    itemList?.let { weatherDetail ->
        Scaffold(topBar = { topAppBar() }) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(contentPadding = it, modifier = Modifier.fillMaxWidth()) {
                    item {
                        Text(text = "Every 5 Second's Weather Details Will Get Update", color = Color.Red,textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 5.dp))
                        Text(text = "Country: ${weatherDetail.sys.country}, State: " + weatherDetail.name, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(10.dp))
                        Title(weather = "Weather Detail's: "+weatherDetail.base + "\n" + weatherDetail.clouds + "\n" + weatherDetail.coord + "\n" + "Date and Time: ${timeToDate(weatherDetail.dt.toLong())}" + "\n" + weatherDetail.id + "\n" + "Id: ${weatherDetail.id}" + "\n" + "Main: ${weatherDetail.main}" + "\n" + weatherDetail.sys + "\n" + weatherDetail.timezone + "\n" + weatherDetail.visibility + "\n" + weatherDetail.wind)
                    }
                    items(weatherDetail.weather) { weatherReport ->
                        ItemRow(weatherReport)
                    }
                }
            }
        }
    }
}

@Composable
fun Title(weather: String) {
    Text(text = weather, style = MaterialTheme.typography.bodyLarge, overflow = TextOverflow.Ellipsis, modifier = Modifier.fillMaxWidth().padding(8.dp))
}

@Composable
fun ItemRow(weather: Weather) {
    Row(modifier = Modifier.padding(8.dp)) {
        Column {
            Text(
                text = "ID: ${weather.id}",
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Main: ${weather.main}",
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Description: ${weather.description}",
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis
            )
            AsyncImage(
                model = weather.icon,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(100.dp).height(100.dp).padding(2.dp)
            )
        }
    }
}