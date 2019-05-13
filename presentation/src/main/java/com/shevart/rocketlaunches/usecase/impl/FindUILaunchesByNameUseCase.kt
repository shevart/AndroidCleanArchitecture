package com.shevart.rocketlaunches.usecase.impl

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.models.launch.SimplePageResult
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import com.shevart.rocketlaunches.di.name.UIMapperNames
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import javax.inject.Inject
import javax.inject.Named

class FindUILaunchesByNameUseCase
@Inject constructor(
    private val findLaunchesByNameUseCase: LaunchesUseCase.FindLaunchesByName,
    @Named(UIMapperNames.UI_MAPPER_LAUNCH)
    private val launchesMapper: Mapper<RocketLaunch, UILaunch>,
    private val schedulerProvider: SchedulerProvider
) : UILaunchesUseCase.FindUILaunchesByName {
    override fun execute(name: String, showedItems: Int) =
        findLaunchesByNameUseCase.execute(name, showedItems)
            .map(this::mapResult)
            .subscribeOnIoObserveOnMain(schedulerProvider)

    private fun mapResult(result: SimplePageResult<RocketLaunch>): SimplePageResult<UILaunch> {
        return SimplePageResult(
            launches = launchesMapper.mapList(result.launches),
            hasMoreItems = result.hasMoreItems
        )
    }
}