package com.shevart.data.remote.impl

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiLaunch
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.data.remote.impl.rest.api.LaunchApi
import com.shevart.data.util.convertLaunchesResult
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class NetworkProvider
@Inject constructor(
    private val launchApi: LaunchApi,
    @Named(DataMapperName.DATA_MAPPER_ROCKET_LAUNCH)
    private val launchMapper: Mapper<ApiLaunch, RocketLaunch>
) : RemoteDataProvider {
    override fun getRocketLaunches(count: Int, offset: Int): Single<PageResult<RocketLaunch>> =
        launchApi
            .getLaunches(count, offset)
            .map { it.convertLaunchesResult(launchMapper) }

    override fun getRocketLaunchById(launchId: Long): Single<RocketLaunch> =
        launchApi
            .getLaunchById(launchId)
            .map { it.launches.first() }
            .map(launchMapper::map)

    override fun getRocketLaunchesByName(
        name: String,
        count: Int,
        offset: Int
    ): Single<PageResult<RocketLaunch>> =
        launchApi
            .getLaunchesByName(name, offset, count)
            .map { it.convertLaunchesResult(launchMapper) }
}