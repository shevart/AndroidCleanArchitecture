package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.app.AppConfigProvider
import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.contract.data.PageRequest
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.convertPageResultToSimplePageResult
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import javax.inject.Inject

class FindLaunchesByNameUseCase
@Inject constructor(
    private val launchesSection: DataSource.LaunchesSection,
    private val appConfigProvider: AppConfigProvider,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.FindLaunchesByName {
    override fun execute(name: String, showedItems: Int) =
        launchesSection
            .getLaunchesByName(prepareRequest(name, showedItems))
            .convertPageResultToSimplePageResult()
            .subscribeOnIoObserveOnMain(schedulerProvider)

    private fun prepareRequest(name: String, showedItems: Int) = PageRequest(
        offset = showedItems,
        count = appConfigProvider.getLaunchesPerPageCount(),
        name = name
    )
}