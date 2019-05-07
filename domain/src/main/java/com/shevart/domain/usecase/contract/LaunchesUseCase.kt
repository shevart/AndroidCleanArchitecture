package com.shevart.domain.usecase.contract

import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Single

interface LaunchesUseCase {
    interface GetNextLaunchesPage {
        fun execute(showedItems: Int): Single<Result>

        data class Result(
            val launches: List<RocketLaunch>,
            val hasMoreItems: Boolean
        )
    }
}