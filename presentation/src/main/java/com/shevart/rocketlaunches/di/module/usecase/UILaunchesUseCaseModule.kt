@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module.usecase

import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.usecase.impl.*
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

    @Binds
    abstract fun bindUpdateUILaunchFavoriteFieldUseCase(
        useCase: UpdateUILaunchFavoriteFieldUseCase
    ): UILaunchesUseCase.UpdateUILaunchFavoriteField

    @Binds
    abstract fun bindFindUILaunchesByNameUseCase(
        useCase: FindUILaunchesByNameUseCase
    ): UILaunchesUseCase.FindUILaunchesByName
}