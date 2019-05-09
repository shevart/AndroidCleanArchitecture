@file:Suppress("unused")

package com.shevart.data.di

import com.shevart.data.datasource.DataSourceRepository
import com.shevart.domain.contract.data.DataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDataSource(impl: DataSourceRepository): DataSource
}
