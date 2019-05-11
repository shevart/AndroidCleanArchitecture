package com.shevart.rocketlaunches.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UILaunch(
    val id: Long,
    val name: String,
    @DrawableRes
    val countryFlagResId: Int,
    @StringRes
    val countryNameResId: Int,
    @DrawableRes
    val favoritesIconResId: Int,
    val favorite: Boolean,
    val date: String,
    val status: UILaunchStatus,
    val imageUrl: String? = null,
    val wikiUrl: String? = null
)