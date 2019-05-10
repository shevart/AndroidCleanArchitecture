package com.shevart.rocketlaunches.core.resources.impl

import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.core.resources.StringProvider
import javax.inject.Inject

class AndroidStringProvider
@Inject constructor() : StringProvider {
    override val statusLaunchingNow = R.string.launch_status_launching_now
    override val statusSuccessfully = R.string.launch_status_successfully
    override val statusFailed = R.string.launch_status_failed
    override val statusInFlight = R.string.launch_status_in_flight
    override val statusCanceled = R.string.launch_status_canceled
    override val statusScheduled = R.string.launch_status_scheduled
    override val statusHold = R.string.launch_status_hold
    override val statusUnknown = R.string.launch_status_unknown

    override val countryUSA = R.string.country_name_usa
    override val countryChina = R.string.country_name_china
    override val countryIndian = R.string.country_name_indian
    override val countryRussia = R.string.country_name_russia
    override val countryUnknown = R.string.country_name_unknown
}