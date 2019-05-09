package com.shevart.data.remote

import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Single

interface RemoteDataProvider {
    fun getRocketLaunches(count: Int, offset: Int = 0): Single<PageResult<RocketLaunch>>
}