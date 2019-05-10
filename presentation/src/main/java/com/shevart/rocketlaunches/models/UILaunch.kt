package com.shevart.rocketlaunches.models

import androidx.annotation.DrawableRes

data class UILaunch(
    val id: Long,
    val name: String,
    @DrawableRes
    val countryFlagResId: Int,
    val countryName: String,
    @DrawableRes
    val favoritesIconResId: Int,
    val date: String,
    val status: UILaunchStatus,
    val imageUrl: String? = null
)