package com.shevart.domain.usecase.contract

import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface LaunchesUseCase {
    interface GetNextLaunchesPage {
        fun execute(showedItems: Int): Single<Result>

        data class Result(
            val launches: List<RocketLaunch>,
            val hasMoreItems: Boolean
        )
    }

    interface GetLaunchById {
        fun execute(launchId: Long): Single<DataWrapper<RocketLaunch>>
    }

    interface GetFavoriteLaunches {
        fun execute(): Single<List<RocketLaunch>>
    }

    interface AddLaunchToFavorites {
        fun execute(launchId: Long): Completable
    }

    interface RemoveLaunchFromFavorites {
        fun execute(launchId: Long): Completable
    }

    interface GetFavoriteChangesObservable {
        fun execute(): Observable<Event>

        data class Event(
            val launchId: Long,
            val action: Action
        ) {
            enum class Action {
                Added, Removed
            }
        }
    }
}