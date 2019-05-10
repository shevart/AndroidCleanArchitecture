@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module

import com.shevart.domain.contract.app.AppConfigProvider
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.rocketlaunches.core.app.AppConfigProviderImpl
import com.shevart.rocketlaunches.core.resources.ResourceProvider
import com.shevart.rocketlaunches.core.resources.StringProvider
import com.shevart.rocketlaunches.core.resources.impl.AndroidResourceProvider
import com.shevart.rocketlaunches.core.resources.impl.AndroidStringProvider
import com.shevart.rocketlaunches.core.util.scheduler.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {
    @Singleton
    @Binds
    abstract fun bindSchedulerProvider(
        schedulerProviderImpl: SchedulerProviderImpl
    ): SchedulerProvider

    @Singleton
    @Binds
    abstract fun bindAppConfigProvider(
        schedulerProviderImpl: AppConfigProviderImpl
    ): AppConfigProvider

    @Singleton
    @Binds
    abstract fun bindResourceProvider(
        impl: AndroidResourceProvider
    ): ResourceProvider

    @Singleton
    @Binds
    abstract fun bindStringProvider(
        impl: AndroidStringProvider
    ): StringProvider
}