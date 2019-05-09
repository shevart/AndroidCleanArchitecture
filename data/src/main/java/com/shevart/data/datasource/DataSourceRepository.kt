package com.shevart.data.datasource

import com.shevart.data.datasource.sections.LaunchesRepository
import com.shevart.data.local.LocalDataProvider
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.domain.contract.data.DataSource
import javax.inject.Inject

class DataSourceRepository
@Inject constructor(
    remoteDataProvider: RemoteDataProvider,
    localDataProvider: LocalDataProvider
) : DataSource {
    private val launchesSection: DataSource.LaunchesSection

    init {
        launchesSection = LaunchesRepository(remoteDataProvider, localDataProvider)
    }

    override fun getLaunchesSection() = launchesSection
}