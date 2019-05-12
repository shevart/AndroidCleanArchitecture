package com.shevart.rocketlaunches.screen.util

import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.models.UILaunchStatus

const val LAUNCH_ID = 100L

val launch = UILaunch(
    id = 123,
    name = "",
    countryFlagResId = 0,
    countryNameResId = 0,
    favorite = false,
    favoritesIconResId = 0,
    date = "date",
    status = UILaunchStatus(
        statusResId = 0,
        backgroundResId = 0
    ),
    wikiUrl = "wikiUrl"
)

val launchesList = listOf(
    launch.copy(id = 1),
    launch.copy(id = 2),
    launch.copy(id = 3)
)
