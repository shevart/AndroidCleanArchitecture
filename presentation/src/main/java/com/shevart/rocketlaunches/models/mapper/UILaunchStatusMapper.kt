package com.shevart.rocketlaunches.models.mapper

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.LaunchStatus
import com.shevart.domain.models.launch.LaunchStatus.*
import com.shevart.rocketlaunches.core.resources.ResourceProvider
import com.shevart.rocketlaunches.core.resources.StringProvider
import com.shevart.rocketlaunches.models.UILaunchStatus
import javax.inject.Inject

class UILaunchStatusMapper
@Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val stringProvider: StringProvider
) : Mapper<LaunchStatus, UILaunchStatus>() {
    override fun map(from: LaunchStatus) = UILaunchStatus(
        statusResId = from.getStatusResId(stringProvider),
        backgroundResId = from.getBackgroundResId(resourceProvider)
    )

    private fun LaunchStatus.getStatusResId(stringProvider: StringProvider) =
        when (this) {
            is LaunchingNow -> stringProvider.statusLaunchingNow
            is Successfully -> stringProvider.statusSuccessfully
            is Failed -> stringProvider.statusFailed
            is InFlight -> stringProvider.statusInFlight
            is Canceled -> stringProvider.statusCanceled
            is Scheduled -> stringProvider.statusScheduled
            is Hold -> stringProvider.statusHold
            is Unknown -> stringProvider.statusUnknown
        }

    private fun LaunchStatus.getBackgroundResId(resourceProvider: ResourceProvider) =
        when (this) {
            is LaunchingNow -> resourceProvider.statusLaunchingNowBg
            is Successfully -> resourceProvider.statusSuccessfullyBg
            is Failed -> resourceProvider.statusFailedBg
            is InFlight -> resourceProvider.statusInFlightBg
            is Canceled -> resourceProvider.statusCanceledBg
            is Scheduled -> resourceProvider.statusScheduledBg
            is Hold -> resourceProvider.statusHoldBg
            is Unknown -> resourceProvider.statusUnknownBg
        }
}