package com.shevart.data.di

import com.shevart.domain.contract.data.DataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceSectionModule {
    @Provides
    fun provideLaunchesSection(dataSource: DataSource): DataSource.LaunchesSection =
            dataSource.getLaunchesSection()
}