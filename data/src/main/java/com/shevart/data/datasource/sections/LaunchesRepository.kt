package com.shevart.data.datasource.sections

import com.shevart.data.local.LocalDataProvider
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.contract.data.FetchPolicy
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

    override fun getLaunches(
        param: PageRequest,
        fetchPolicy: FetchPolicy
    ): Single<PageResult<RocketLaunch>> {
        TODO()
    }
}