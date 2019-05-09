package com.shevart.data.di

import com.shevart.data.remote.impl.rest.api.LaunchApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideLaunchApi(retrofit: Retrofit): LaunchApi =
        retrofit.create(LaunchApi::class.java)
}