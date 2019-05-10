package com.shevart.rocketlaunches.util.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.shevart.domain.models.launch.Country
import com.shevart.domain.models.launch.Country.*
import com.shevart.domain.models.launch.LaunchStatus
import com.shevart.domain.models.launch.LaunchStatus.*
import com.shevart.domain.models.launch.LaunchStatus.Unknown
import com.shevart.rocketlaunches.core.resources.ResourceProvider
import com.shevart.rocketlaunches.core.resources.StringProvider

@StringRes
fun StringProvider.getLaunchStatusResId(launchStatus: LaunchStatus) =
    when (launchStatus) {
        is LaunchingNow -> statusLaunchingNow
        is Successfully -> statusSuccessfully
        is Failed -> statusFailed
        is InFlight -> statusInFlight
        is Canceled -> statusCanceled
        is Scheduled -> statusScheduled
        is Hold -> statusHold
        is Unknown -> statusUnknown
    }

@DrawableRes
fun ResourceProvider.getLaunchStatusBackgroundResId(launchStatus: LaunchStatus) =
    when (launchStatus) {
        is LaunchingNow -> statusLaunchingNowBg
        is Successfully -> statusSuccessfullyBg
        is Failed -> statusFailedBg
        is InFlight -> statusInFlightBg
        is Canceled -> statusCanceledBg
        is Scheduled -> statusScheduledBg
        is Hold -> statusHoldBg
        is Unknown -> statusUnknownBg
    }

@DrawableRes
fun ResourceProvider.getCountryFlagResId(country: Country) =
    when (country) {
        is USA -> flagUsa
        is Russia -> flagRussia
        is Indian -> flagIndian
        is China -> flagChine
        else -> flagUnknown
    }

@StringRes
fun StringProvider.getCountryNameResId(country: Country) =
    when (country) {
        is USA -> countryUSA
        is Russia -> countryRussia
        is Indian -> countryIndian
        is China -> countryChina
        else -> countryUnknown
    }

@DrawableRes
fun ResourceProvider.getFavoriteResId(favorite: Boolean) =
    if (favorite) {
        favoriteActive
    } else {
        favoriteNonActive
    }