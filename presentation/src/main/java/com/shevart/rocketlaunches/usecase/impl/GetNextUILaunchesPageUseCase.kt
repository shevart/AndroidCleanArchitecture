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

class GetNextUILaunchesPageUseCase
@Inject constructor(
    private val getNextLaunchesPageUseCase: LaunchesUseCase.GetNextLaunchesPage,
    private val schedulerProvider: SchedulerProvider,
    @Named(UIMapperNames.UI_MAPPER_LAUNCH)
    private val launchesMapper: Mapper<RocketLaunch, UILaunch>
) : UILaunchesUseCase.GetNextUILaunchesPage {
    override fun execute(showedItems: Int) =
        getNextLaunchesPageUseCase.execute(showedItems)
            .map(this::mapResult)
            .subscribeOnIoObserveOnMain(schedulerProvider)

    private fun mapResult(result: SimplePageResult<RocketLaunch>): SimplePageResult<UILaunch> {
        return SimplePageResult(
            launches = launchesMapper.mapList(result.launches),
            hasMoreItems = result.hasMoreItems
        )
    }
}