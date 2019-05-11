@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module.usecase

import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.usecase.impl.GetLaunchByIdUseCase
import com.shevart.domain.usecase.impl.GetNextLaunchesPageUseCase
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
}