package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetNextLaunchesPage.Result
import io.reactivex.Single
import javax.inject.Inject

class GetNextLaunchesPageUseCase
@Inject constructor(
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.GetNextLaunchesPage {
    override fun execute(showedItems: Int): Single<Result> {
        TODO()
    }
}