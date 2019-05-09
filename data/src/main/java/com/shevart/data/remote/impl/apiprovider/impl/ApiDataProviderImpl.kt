package com.shevart.data.remote.impl.apiprovider.impl

import com.shevart.data.BuildConfig
import com.shevart.data.remote.impl.apiprovider.ApiDataProvider

class ApiDataProviderImpl : ApiDataProvider {
    override fun isDebug() = BuildConfig.DEBUG

    override fun provideBaseApiUrl() = BuildConfig.API_BASE_URL
}