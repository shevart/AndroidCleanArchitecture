package com.shevart.rocketlaunches.models.mapper

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.LaunchStatus
import com.shevart.domain.models.launch.LaunchStatus.*
import com.shevart.rocketlaunches.core.resources.ResourceProvider
import com.shevart.rocketlaunches.core.resources.StringProvider
import com.shevart.rocketlaunches.models.UILaunchStatus
import com.shevart.rocketlaunches.util.ui.getLaunchStatusBackgroundResId
import com.shevart.rocketlaunches.util.ui.getLaunchStatusResId
import javax.inject.Inject

class UILaunchStatusMapper
@Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val stringProvider: StringProvider
) : Mapper<LaunchStatus, UILaunchStatus>() {
    override fun map(from: LaunchStatus) = UILaunchStatus(
        statusResId = stringProvider.getLaunchStatusResId(from),
        backgroundResId = resourceProvider.getLaunchStatusBackgroundResId(from)
    )
}