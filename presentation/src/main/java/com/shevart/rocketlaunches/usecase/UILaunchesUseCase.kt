package com.shevart.rocketlaunches.usecase

import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.models.launch.SimplePageResult
import com.shevart.rocketlaunches.models.UILaunch
import io.reactivex.Single

/**
 * Wrapper for domain's useCases. I use it for as adapter for simplify work with tests.
 */
interface UILaunchesUseCase {
    interface GetNextUILaunchesPage {
        fun execute(showedItems: Int): Single<SimplePageResult<UILaunch>>
    }

    interface GetUILaunchById {
        fun execute(launchId: Long): Single<DataWrapper<UILaunch>>
    }

    interface GetUIFavoriteLaunches {
        fun execute(): Single<List<UILaunch>>
    }

    /**
     * Update UILaunch favorite field and UI favorite icon field
     */
    interface UpdateUILaunchFavoriteField {
        fun execute(launch: UILaunch, favorite: Boolean): UILaunch
    }

    interface FindLaunchesByName {
        fun execute(name: String, showedItems: Int): Single<SimplePageResult<UILaunch>>
    }
}