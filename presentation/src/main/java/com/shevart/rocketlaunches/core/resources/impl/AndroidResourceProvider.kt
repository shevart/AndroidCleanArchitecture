package com.shevart.rocketlaunches.core.resources.impl

import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.core.resources.ResourceProvider
import javax.inject.Inject

class AndroidResourceProvider
@Inject constructor() : ResourceProvider {
    override val statusLaunchingNowBg = R.drawable.gradient_status_orange
    override val statusSuccessfullyBg = R.drawable.gradient_status_green
    override val statusFailedBg = R.drawable.gradient_status_red
    override val statusInFlightBg = R.drawable.gradient_status_blue
    override val statusCanceledBg = R.drawable.gradient_status_grey
    override val statusScheduledBg = R.drawable.gradient_status_grey_light
    override val statusHoldBg = R.drawable.gradient_status_grey_light
    override val statusUnknownBg = R.drawable.gradient_status_grey

    override val flagUsa = R.drawable.flag_usa
    override val flagRussia = R.drawable.flag_russia
    override val flagIndian = R.drawable.flag_indian
    override val flagChine = R.drawable.flag_china
    override val flagUnknown = R.drawable.flag_unknown

    override val favoriteActive = R.drawable.ic_favorite_red
    override val favoriteNonActive = R.drawable.ic_favorite_white
}