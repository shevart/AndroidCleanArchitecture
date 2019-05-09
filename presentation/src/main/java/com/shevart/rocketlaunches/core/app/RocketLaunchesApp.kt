package com.shevart.rocketlaunches.core.app

import android.app.Application
import com.facebook.stetho.Stetho
import com.shevart.rocketlaunches.BuildConfig
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.di.component.DaggerAppComponent
import com.shevart.rocketlaunches.di.module.AppModule

class RocketLaunchesApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    fun getAppComponent() = appComponent
}