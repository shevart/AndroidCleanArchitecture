package com.shevart.rocketlaunches.util.ui

import java.text.DateFormat
import java.util.*

fun Date.toShortStr() =
    DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this)