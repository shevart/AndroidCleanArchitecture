package com.shevart.data.remote.impl

import com.shevart.data.models.mapper.LaunchMapper
import com.shevart.data.models.mapper.RocketMapper
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.data.remote.impl.rest.api.LaunchApi
import com.shevart.data.remote.impl.rest.response.GetLaunchesResponse
import com.shevart.data.util.convertLaunchesResult
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Single
import javax.inject.Inject

class NetworkProvider
@Inject constructor(
    private val launchApi: LaunchApi
) : RemoteDataProvider {
    private val launchMapper = LaunchMapper()

    override fun getRocketLaunches(count: Int, offset: Int): Single<PageResult<RocketLaunch>> =
        launchApi.getLaunches(count)
            .map { it.convertLaunchesResult(launchMapper) }
}