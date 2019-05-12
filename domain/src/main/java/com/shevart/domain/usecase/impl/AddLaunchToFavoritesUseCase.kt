package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.data.DataSource.LaunchesSection
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.util.mapForceObtainDataFromDataWrapper
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import javax.inject.Inject

class AddLaunchToFavoritesUseCase
@Inject constructor(
    private val launchesSection: LaunchesSection,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.AddLaunchToFavorites {
    override fun execute(launchId: Long) =
        launchesSection
            .getLaunchById(launchId)
            .mapForceObtainDataFromDataWrapper()
            .flatMapCompletable(launchesSection::addLaunchToFavorites)
            .subscribeOnIoObserveOnMain(schedulerProvider)
}