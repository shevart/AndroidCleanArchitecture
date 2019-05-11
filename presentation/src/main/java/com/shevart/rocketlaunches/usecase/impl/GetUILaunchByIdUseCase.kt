package com.shevart.rocketlaunches.usecase.impl

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import com.shevart.rocketlaunches.di.name.UIMapperNames
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import javax.inject.Inject
import javax.inject.Named

class GetUILaunchByIdUseCase
@Inject constructor(
    private val getLaunchByIdUseCase: LaunchesUseCase.GetLaunchById,
    @Named(UIMapperNames.UI_MAPPER_LAUNCH)
    private val launchMapper: Mapper<RocketLaunch, UILaunch>,
    private val schedulerProvider: SchedulerProvider
) : UILaunchesUseCase.GetUILaunchById {
    override fun execute(launchId: Long) =
        getLaunchByIdUseCase.execute(launchId)
            .map(this::mapLaunch)
            .subscribeOnIoObserveOnMain(schedulerProvider)

    private fun mapLaunch(launch: DataWrapper<RocketLaunch>): DataWrapper<UILaunch> {
        return if (launch.data != null) {
            DataWrapper(launchMapper.map(launch.data!!))
        } else {
            DataWrapper.empty()
        }
    }
}