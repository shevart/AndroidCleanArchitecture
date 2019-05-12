package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import javax.inject.Inject

class GetFavoriteLaunchesUseCase
@Inject constructor(
    private val launchesSection: DataSource.LaunchesSection,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.GetFavoriteLaunches {
    override fun execute() =
        launchesSection
            .getFavoritesLaunches()
            .subscribeOnIoObserveOnMain(schedulerProvider)
}