package com.shevart.rocketlaunches.usecase.impl

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase.GetNextUILaunchesPage.Result
import io.reactivex.Single
import javax.inject.Inject

class GetNextUILaunchesPageUseCase
@Inject constructor(
    private val getNextLaunchesPageUseCase: LaunchesUseCase.GetNextLaunchesPage,
    private val schedulerProvider: SchedulerProvider
) : UILaunchesUseCase.GetNextUILaunchesPage {
    private val launchesMapper: Mapper<RocketLaunch, UILaunch> = TODO()

    override fun execute(showedItems: Int): Single<Result> {
        TODO()
    }
}