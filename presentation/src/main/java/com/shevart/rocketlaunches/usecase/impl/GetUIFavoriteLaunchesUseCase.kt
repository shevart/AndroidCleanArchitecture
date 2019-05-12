package com.shevart.rocketlaunches.usecase.impl

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetFavoriteLaunches
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import com.shevart.rocketlaunches.di.name.UIMapperNames
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import javax.inject.Inject
import javax.inject.Named

class GetUIFavoriteLaunchesUseCase
@Inject constructor(
    private val getFavoriteLaunchesUseCase: GetFavoriteLaunches,
    @Named(UIMapperNames.UI_MAPPER_LAUNCH)
    private val launchMapper: Mapper<RocketLaunch, UILaunch>,
    private val schedulerProvider: SchedulerProvider
) : UILaunchesUseCase.GetUIFavoriteLaunches {
    override fun execute() =
        getFavoriteLaunchesUseCase.execute()
            .map(launchMapper::mapList)
            .subscribeOnIoObserveOnMain(schedulerProvider)
}