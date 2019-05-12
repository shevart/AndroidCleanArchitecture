@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module.usecase

import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.usecase.impl.GetNextUILaunchesPageUseCase
import com.shevart.rocketlaunches.usecase.impl.GetUIFavoriteLaunchesUseCase
import com.shevart.rocketlaunches.usecase.impl.GetUILaunchByIdUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UILaunchesUseCaseModule {
    @Binds
    abstract fun bindGetNextUILaunchesPageUseCase(
        useCase: GetNextUILaunchesPageUseCase
    ): UILaunchesUseCase.GetNextUILaunchesPage

    @Binds
    abstract fun bindGetUILaunchByIdUseCase(
        useCase: GetUILaunchByIdUseCase
    ): UILaunchesUseCase.GetUILaunchById

    @Binds
    abstract fun bindGetUIFavoriteLaunchesUseCase(
        useCase: GetUIFavoriteLaunchesUseCase
    ): UILaunchesUseCase.GetUIFavoriteLaunches
}