@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shevart.rocketlaunches.base.mvvm.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
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