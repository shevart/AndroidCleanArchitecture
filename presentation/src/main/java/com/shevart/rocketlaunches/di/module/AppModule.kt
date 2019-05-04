package com.shevart.rocketlaunches.di.module

import android.content.Context
import com.shevart.rocketlaunches.core.app.RocketLaunchesApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: RocketLaunchesApp) {
    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext
}