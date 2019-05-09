package com.shevart.domain.contract.data

import com.shevart.domain.contract.data.FetchPolicy.SESSION_CACHE_OR_REMOTE
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Single

interface DataSource {
    fun getLaunchesSection(): LaunchesSection

    interface LaunchesSection {
        fun getLaunches(
            param: PageRequest,
            fetchPolicy: FetchPolicy = SESSION_CACHE_OR_REMOTE
        ): Single<PageResult<RocketLaunch>>
    }
}