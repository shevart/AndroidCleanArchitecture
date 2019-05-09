package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.app.AppConfigProvider
import com.shevart.domain.contract.data.DataSource.LaunchesSection
import com.shevart.domain.contract.data.PageRequest
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetNextLaunchesPage.Result
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import io.reactivex.Single
import javax.inject.Inject

class GetNextLaunchesPageUseCase
@Inject constructor(
    private val launchesSection: LaunchesSection,
    private val appConfigProvider: AppConfigProvider,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.GetNextLaunchesPage {
    override fun execute(showedItems: Int) = launchesSection
        .getLaunches(prepareRequest(showedItems))
        .map(this::convertResult)
        .subscribeOnIoObserveOnMain(schedulerProvider)

    private fun prepareRequest(showedItems: Int) = PageRequest(
        offset = showedItems,
        count = appConfigProvider.getLaunchesPerPageCount()
    )

    private fun convertResult(result: PageResult<RocketLaunch>) = Result(
        launches = result.items,
        hasMoreItems = (result.offset + result.count) < result.totalCount
    )
}