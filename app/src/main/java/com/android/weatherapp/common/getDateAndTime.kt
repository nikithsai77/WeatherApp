package com.android.weatherapp.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun timeToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.format(date)
}