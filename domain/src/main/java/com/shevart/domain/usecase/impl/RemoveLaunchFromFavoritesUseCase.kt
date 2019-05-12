package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.data.DataSource.LaunchesSection
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.mapForceObtainDataFromDataWrapper
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import javax.inject.Inject

class RemoveLaunchFromFavoritesUseCase
@Inject constructor(
    private val launchesSection: LaunchesSection,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.RemoveLaunchFromFavorites {
    override fun execute(launchId: Long) =
        launchesSection
            .getLaunchById(launchId)
            .mapForceObtainDataFromDataWrapper()
            .flatMapCompletable(launchesSection::removeLaunchFromFavorites)
            .subscribeOnIoObserveOnMain(schedulerProvider)
}