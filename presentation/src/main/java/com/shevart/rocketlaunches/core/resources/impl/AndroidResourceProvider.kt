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
}