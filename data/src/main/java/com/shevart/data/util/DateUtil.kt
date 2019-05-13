package com.shevart.data.util

import java.text.SimpleDateFormat
import java.util.*

private const val API_DATE_FORMAT = "MMMM d, yyyy"
private const val UTC = "UTC"

private val formatter = SimpleDateFormat(API_DATE_FORMAT, Locale.ENGLISH).apply {
    this.timeZone = TimeZone.getTimeZone(UTC)
}

fun String.convertApiDateToDate(): Date {
    return formatter.parse(this)
}