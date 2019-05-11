package com.shevart.rocketlaunches.usecase

import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.rocketlaunches.models.UILaunch
import io.reactivex.Single

/**
 * Wrapper for domain's useCases. I use it for as adapter for simplify work with tests.
 */
interface UILaunchesUseCase {
    interface GetNextUILaunchesPage {
        fun execute(showedItems: Int): Single<UIResult>

        data class UIResult(
            val launches: List<UILaunch>,
            val hasMoreItems: Boolean
        )
    }

    interface GetUILaunchById {
        fun execute(launchId: Long): Single<DataWrapper<UILaunch>>
    }
}