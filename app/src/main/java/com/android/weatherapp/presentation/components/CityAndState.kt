@file:OptIn(ExperimentalMaterial3Api::class)

package com.android.weatherapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

@Composable
fun CityAndState(content: @Composable () -> Unit, cityBlock: (String) -> Unit) {
    var count by remember {
        mutableStateOf(value = 0)
    }
    var city by remember {
        mutableStateOf(value = "")
    }
    var stateCode by remember {
        mutableStateOf(value = "")
    }
    var countryCode by remember {
        mutableStateOf(value = "")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(value = city, placeholder = { Text(text = "Enter City Name") }, onValueChange = { city = it }, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        OutlinedTextField(value = stateCode, placeholder = { Text(text = "Enter State Code") },onValueChange = { if (stateCode.all {all -> all.toString().isDigitsOnly() } && stateCode.length <= 5 && it.isDigitsOnly()) { stateCode  = it } }, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = countryCode, placeholder = { Text(text = "Enter Country Code") },onValueChange = { if (countryCode.all {all -> all.toString().isDigitsOnly() } && countryCode.length <= 5 && it.isDigitsOnly()) { countryCode  = it } }, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(onClick = {
            var getAllInfo = ""
            if (city.trim().isNotEmpty()) getAllInfo = city
            else if (stateCode.trim().isNotEmpty()) {
                if (getAllInfo.trim().isNotEmpty()) getAllInfo+=","
                getAllInfo += stateCode
            }
            else if (countryCode.trim().isNotEmpty()) {
                if (getAllInfo.trim().isNotEmpty()) getAllInfo+=","
                getAllInfo += "+1$countryCode"
            }
            if (getAllInfo.trim().isNotEmpty()) {
                count++
                cityBlock(getAllInfo)
            }
        }, modifier = Modifier
            .align(CenterHorizontally)
            .padding(6.dp)) {
            Text(text = "Make Request")
        }
        if (count > 0) content()
    }
}