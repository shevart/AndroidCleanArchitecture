@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module.usecase

import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.usecase.impl.GetNextUILaunchesPageUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UILaunchesUseCaseModule {
    @Binds
    abstract fun bindGetNextUILaunchesPageUseCase(
        useCase: GetNextUILaunchesPageUseCase
    ): UILaunchesUseCase.GetNextUILaunchesPage
}