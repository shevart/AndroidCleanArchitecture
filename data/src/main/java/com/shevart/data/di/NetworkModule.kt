package com.shevart.data.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiLaunch
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.data.remote.impl.NetworkProvider
import com.shevart.data.remote.impl.apiprovider.ApiDataProvider
import com.shevart.data.remote.impl.apiprovider.impl.ApiDataProviderImpl
import com.shevart.data.remote.impl.rest.api.LaunchApi
import com.shevart.data.util.GsonUtil
import com.shevart.data.util.addNetworkInterceptorByPredicate
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.RocketLaunch
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRemoteDataProvider(
        launchApi: LaunchApi,
        @Named(DataMapperName.DATA_MAPPER_ROCKET_LAUNCH)
        launchMapper: Mapper<ApiLaunch, RocketLaunch>
    ): RemoteDataProvider =
        NetworkProvider(
            launchApi = launchApi,
            launchMapper = launchMapper
        )

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        callAdapterFactory: CallAdapter.Factory,
        apiDataProvider: ApiDataProvider
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(apiDataProvider.provideBaseApiUrl())
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(apiDataProvider: ApiDataProvider): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptorByPredicate(apiDataProvider.isDebug()) { StethoInterceptor() }
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory =
        RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonUtil.getGson()

    @Provides
    @Singleton
    fun provideApiDataProvider(): ApiDataProvider =
        ApiDataProviderImpl()
}