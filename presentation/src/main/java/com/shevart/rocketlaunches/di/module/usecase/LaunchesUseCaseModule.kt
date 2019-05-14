@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module.usecase

import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.usecase.impl.*
import dagger.Binds
import dagger.Module

@Module
abstract class LaunchesUseCaseModule {
    @Binds
    abstract fun bindGetNextLaunchesPageUseCase(
        useCase: GetNextLaunchesPageUseCase
    ): LaunchesUseCase.GetNextLaunchesPage

    @Binds
    abstract fun bindGetLaunchByIdUseCase(
        useCase: GetLaunchByIdUseCase
    ): LaunchesUseCase.GetLaunchById

    @Binds
    abstract fun bindGetFavoritesLaunchesUseCase(
        useCase: GetFavoriteLaunchesUseCase
    ): LaunchesUseCase.GetFavoriteLaunches

    @Binds
    abstract fun bindAddLaunchToFavoritesUseCase(
        useCase: AddLaunchToFavoritesUseCase
    ): LaunchesUseCase.AddLaunchToFavorites

    @Binds
    abstract fun bindRemoveLaunchFromFavoritesUseCase(
        useCase: RemoveLaunchFromFavoritesUseCase
    ): LaunchesUseCase.RemoveLaunchFromFavorites

    @Binds
    abstract fun bindGetFavoriteChangesObservableUseCase(
        useCase: GetFavoriteChangesObservableUseCase
    ): LaunchesUseCase.GetFavoriteChangesObservable

    @Binds
    abstract fun bindFindLaunchesByNameUseCase(
        useCase: FindLaunchesByNameUseCase
    ): LaunchesUseCase.FindLaunchesByName
}