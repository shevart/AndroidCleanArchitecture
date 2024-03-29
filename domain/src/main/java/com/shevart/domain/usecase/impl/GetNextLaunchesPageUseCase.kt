package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.app.AppConfigProvider
import com.shevart.domain.contract.data.DataSource.LaunchesSection
import com.shevart.domain.contract.data.PageRequest
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.models.launch.SimplePageResult
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.convertPageResultToSimplePageResult
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import javax.inject.Inject

class GetNextLaunchesPageUseCase
@Inject constructor(
    private val launchesSection: LaunchesSection,
    private val appConfigProvider: AppConfigProvider,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.GetNextLaunchesPage {
    override fun execute(showedItems: Int) =
        launchesSection
            .getLaunches(prepareRequest(showedItems))
            .convertPageResultToSimplePageResult()
            .subscribeOnIoObserveOnMain(schedulerProvider)

    private fun prepareRequest(showedItems: Int) = PageRequest(
        offset = showedItems,
        count = appConfigProvider.getLaunchesPerPageCount()
    )
}