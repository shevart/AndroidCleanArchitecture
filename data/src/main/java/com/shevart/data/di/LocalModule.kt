@file:Suppress("unused")

package com.shevart.data.di

import com.shevart.data.local.LocalDataProvider
import com.shevart.data.local.impl.RamLocalDataProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LocalModule {
    @Binds
    @Singleton
    abstract fun bindLocalDataProvider(
        impl: RamLocalDataProvider
    ): LocalDataProvider
}