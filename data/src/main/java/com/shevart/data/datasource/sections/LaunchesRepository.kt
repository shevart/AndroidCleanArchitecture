package com.shevart.data.datasource.sections

import com.shevart.data.local.LocalDataProvider
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.contract.data.FetchPolicy
import com.shevart.domain.contract.data.FetchPolicy.REMOTE_ONLY
import com.shevart.domain.contract.data.PageRequest
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Single
import javax.inject.Inject

class LaunchesRepository
@Inject constructor(
    remoteDataProvider: RemoteDataProvider,
    localDataProvider: LocalDataProvider
) : AbsSection(remoteDataProvider, localDataProvider), DataSource.LaunchesSection {
    private val launches = ArrayList<RocketLaunch>()
    private var totalLaunchesCount = 0

    override fun getLaunches(
        param: PageRequest,
        fetchPolicy: FetchPolicy
    ): Single<PageResult<RocketLaunch>> {
        val loadRemote =
            (fetchPolicy == REMOTE_ONLY) || param.biggerThanCacheItemsCount(launches.size)
        return if (loadRemote) {
            loadLaunchesRemote(param)
        } else {
            getFromCache(param)
        }
    }

    private fun getFromCache(param: PageRequest) =
        Single.just(
            PageResult(
                items = launches.subList(param.offset, (param.offset + param.count)),
                count = param.count + 1,
                offset = param.offset,
                totalCount = totalLaunchesCount
            )
        )


    private fun loadLaunchesRemote(param: PageRequest) =
        remote.getRocketLaunches(param.count, param.offset)
            .doOnSuccess { saveLaunches(param, it) }

    /**
     * There is very simple storage paged data to cache. It will be work
     * fine only if client will be request data consistently, page by page.
     * For other cases - data won't be saved to cache/local storage
     */
    private fun saveLaunches(param: PageRequest, result: PageResult<RocketLaunch>) {
        val firstPage = launches.isEmpty() && param.offset == 0
        val nextPage = launches.size == result.offset
        if (firstPage || nextPage) {
            launches.addAll(result.items)
        }
        totalLaunchesCount = result.totalCount
    }

    private companion object {
        private fun PageRequest.biggerThanCacheItemsCount(itemsCount: Int) =
            (this.offset + this.count) > itemsCount
    }
}