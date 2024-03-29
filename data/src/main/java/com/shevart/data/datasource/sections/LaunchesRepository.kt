package com.shevart.data.datasource.sections

import com.shevart.data.local.LocalDataProvider
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.contract.data.FetchPolicy
import com.shevart.domain.contract.data.FetchPolicy.REMOTE_ONLY
import com.shevart.domain.contract.data.PageRequest
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.util.mapByDataWrapper
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class LaunchesRepository
@Inject constructor(
    remoteDataProvider: RemoteDataProvider,
    localDataProvider: LocalDataProvider
) : AbsSection(remoteDataProvider, localDataProvider), DataSource.LaunchesSection {
    private val launches = ArrayList<RocketLaunch>()
    private var totalLaunchesCount = 0

    private val addedFavoritesSubject = PublishSubject.create<Long>()
    private val removedFavoritesSubject = PublishSubject.create<Long>()

    override fun getLaunches(
        param: PageRequest,
        fetchPolicy: FetchPolicy
    ): Single<PageResult<RocketLaunch>> {
        val loadRemote =
            (fetchPolicy == REMOTE_ONLY) || param.biggerThanCacheItemsCount(launches.size)
        return if (loadRemote) {
            loadLaunchesListRemote(param)
        } else {
            getLaunchesListFromCache(param)
        }
    }

    override fun getLaunchById(
        launchId: Long,
        fetchPolicy: FetchPolicy
    ): Single<DataWrapper<RocketLaunch>> {
        val loadRemote =
            fetchPolicy == REMOTE_ONLY || !launches.hasLaunch(launchId)
        return if (loadRemote) {
            loadRocketLaunchRemote(launchId)
        } else {
            getRocketLaunchFromLocal(launchId)
        }
    }

    override fun getLaunchesByName(param: PageRequest) =
        remote.getRocketLaunchesByName(
            name = param.name,
            offset = param.offset,
            count = param.count
        )

    override fun getFavoritesLaunches() = local.getFavorites()

    override fun addLaunchToFavorites(launch: RocketLaunch) =
        local.addToFavorites(launch)
            .doOnComplete { setLaunchAsFavoriteInCache(launch) }
            .doOnComplete { addedFavoritesSubject.onNext(launch.id) }!!

    override fun removeLaunchFromFavorites(launch: RocketLaunch) =
        local.removeFromFavorites(launch.id)
            .doOnComplete { setLaunchAsNotFavoriteInCache(launch) }
            .doOnComplete { removedFavoritesSubject.onNext(launch.id) }!!

    override fun getLaunchAddedToFavoritesObservable() = addedFavoritesSubject

    override fun getLaunchRemovedFromFavoritesObservable() = removedFavoritesSubject

    private fun getLaunchesListFromCache(param: PageRequest) =
        Single.just(
            PageResult(
                items = launches.subList(param.offset, (param.offset + param.count)),
                count = param.count + 1,
                offset = param.offset,
                totalCount = totalLaunchesCount
            )
        )

    private fun loadLaunchesListRemote(param: PageRequest) =
        remote.getRocketLaunches(param.count, param.offset)
            .flatMap(this::updateFavoriteLaunches)
            .doOnSuccess { saveLaunchesList(param, it) }

    private fun updateFavoriteLaunches(result: PageResult<RocketLaunch>) = loadFavoriteIds()
        .map { favoriteIds ->
            val launches = result.items.toMutableList()
            launches.updateByIds(favoriteIds) { it.copy(favorite = true) }
            return@map result.copy(items = launches)
        }

    /**
     * There is very simple storage paged data to cache. It will be work
     * fine only if client will be request data consistently, page by page.
     * For other cases - data won't be saved to cache/local storage
     */
    private fun saveLaunchesList(param: PageRequest, result: PageResult<RocketLaunch>) {
        val firstPage = launches.isEmpty() && param.offset == 0
        val nextPage = launches.size == result.offset
        if (firstPage || nextPage) {
            launches.addAll(result.items)
        }
        totalLaunchesCount = result.totalCount
    }

    private fun loadRocketLaunchRemote(launchId: Long): Single<DataWrapper<RocketLaunch>> =
        remote
            .getRocketLaunchById(launchId)
            .map(this::setIsFavorite)
            .mapByDataWrapper()

    private fun setIsFavorite(launch: RocketLaunch): RocketLaunch {
        return if (local.isFavorite(launch.id)) {
            launch.copy(favorite = true)
        } else {
            launch
        }
    }

    private fun loadFavoriteIds() = local
        .getFavorites()
        .map { favorites ->
            favorites.map { it.id }
        }

    private fun getRocketLaunchFromLocal(launchId: Long): Single<DataWrapper<RocketLaunch>> =
        Single.fromCallable {
            val launch = launches.find { it.id == launchId }
            return@fromCallable DataWrapper(launch)
        }

    private fun setLaunchAsFavoriteInCache(launch: RocketLaunch) {
        launches.updateById(launch.id) { it.copy(favorite = true) }
    }

    private fun setLaunchAsNotFavoriteInCache(launch: RocketLaunch) {
        launches.updateById(launch.id) { it.copy(favorite = false) }
    }

    private companion object {
        private fun PageRequest.biggerThanCacheItemsCount(itemsCount: Int) =
            (this.offset + this.count) > itemsCount

        private fun List<RocketLaunch>.hasLaunch(launchId: Long) =
            this.find { it.id == launchId } != null

        private inline fun MutableList<RocketLaunch>.updateByIds(
            launchIds: List<Long>,
            update: (origin: RocketLaunch) -> RocketLaunch
        ) {
            launchIds.forEach { launchId ->
                this.updateById(launchId, update)
            }
        }

        private inline fun MutableList<RocketLaunch>.updateById(
            launchId: Long,
            update: (origin: RocketLaunch) -> RocketLaunch
        ) {
            val launch = this.find { it.id == launchId }
            if (launch != null) {
                val launchIndex = this.indexOf(launch)
                this.removeAt(launchIndex)
                this.add(launchIndex, update(launch))
            }
        }

    }
}