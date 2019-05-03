@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module

import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.rocketlaunches.core.util.scheduler.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreModule {
    @Singleton
    @Binds
    abstract fun bindSchedulerProvider(schedulerProviderImpl: SchedulerProviderImpl): SchedulerProvider
}