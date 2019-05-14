package com.shevart.domain.contract.data

import com.shevart.domain.contract.data.FetchPolicy.SESSION_CACHE_OR_REMOTE
import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface DataSource {
    fun getLaunchesSection(): LaunchesSection

    interface LaunchesSection {
        fun getLaunches(
            param: PageRequest,
            fetchPolicy: FetchPolicy = SESSION_CACHE_OR_REMOTE
        ): Single<PageResult<RocketLaunch>>

        fun getLaunchById(
            launchId: Long,
            fetchPolicy: FetchPolicy = SESSION_CACHE_OR_REMOTE
        ): Single<DataWrapper<RocketLaunch>>

        /**
         * Remote only
         */
        fun getLaunchesByName(param: PageRequest): Single<PageResult<RocketLaunch>>

        fun getFavoritesLaunches(): Single<List<RocketLaunch>>

        fun addLaunchToFavorites(launch: RocketLaunch): Completable

        fun removeLaunchFromFavorites(launch: RocketLaunch): Completable

        /**
         * @return [Observable]<[Long]> with launchId which was added to favorites
         */
        fun getLaunchAddedToFavoritesObservable(): Observable<Long>

        /**
         * @return [Observable]<[Long]> with launchId which was removed from favorites
         */
        fun getLaunchRemovedFromFavoritesObservable(): Observable<Long>
    }
}