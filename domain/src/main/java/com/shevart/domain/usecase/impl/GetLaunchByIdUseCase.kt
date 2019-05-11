package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.data.DataSource.LaunchesSection
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import io.reactivex.Single
import javax.inject.Inject

class GetLaunchByIdUseCase
@Inject constructor(
    private val launchesSection: LaunchesSection,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.GetLaunchById {
    override fun execute(launchId: Long): Single<DataWrapper<RocketLaunch>> =
        launchesSection
            .getLaunchById(launchId)
            .subscribeOnIoObserveOnMain(schedulerProvider)
}