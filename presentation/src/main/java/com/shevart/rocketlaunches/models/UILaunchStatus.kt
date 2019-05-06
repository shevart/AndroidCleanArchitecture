package com.shevart.rocketlaunches.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UILaunchStatus(
    @StringRes
    val statusResI: Int,
    @DrawableRes
    val backgroundResId: Int
)