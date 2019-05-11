@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shevart.rocketlaunches.base.mvvm.ViewModelFactory
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    internal abstract fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesListViewModel::class)
    internal abstract fun bindLaunchesListViewModel(viewModel: LaunchesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WikiPageViewModel::class)
    internal abstract fun bindFavoriteWikiPageViewModel(viewModel: WikiPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    internal abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @MustBeDocumented
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
}