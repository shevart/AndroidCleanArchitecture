package com.shevart.rocketlaunches.di.component

import com.shevart.data.di.*
import com.shevart.rocketlaunches.core.app.RocketLaunchesApp
import com.shevart.rocketlaunches.di.module.AppModule
import com.shevart.rocketlaunches.di.module.CoreModule
import com.shevart.rocketlaunches.di.module.ViewModelModule
import com.shevart.rocketlaunches.di.module.usecase.LaunchesUseCaseModule
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesFragment
import com.shevart.rocketlaunches.screen.home.host.MainActivity
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CoreModule::class,
        ViewModelModule::class,
        LaunchesUseCaseModule::class,
        NetworkModule::class,
        ApiModule::class,
        LocalModule::class,
        DataSourceModule::class,
        DataSourceSectionModule::class
    ]
)
interface AppComponent {
    fun inject(app: RocketLaunchesApp)

    fun inject(activity: MainActivity)

    fun inject(fragment: LaunchesListFragment)

    fun inject(fragment: FavoritesFragment)
}