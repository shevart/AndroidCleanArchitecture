package com.shevart.rocketlaunches.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UILaunch(
    val id: Long,
    val name: String,
    @DrawableRes
    val countryFlagResId: Int,
    val countryName: String,
    @DrawableRes
    val favoritesIconResId: Int,
    val date: String,
    val status: UILaunchStatus

)