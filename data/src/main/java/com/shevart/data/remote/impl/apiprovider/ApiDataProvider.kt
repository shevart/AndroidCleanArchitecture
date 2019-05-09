package com.shevart.data.remote.impl.apiprovider

interface ApiDataProvider {
    fun isDebug(): Boolean

    fun provideBaseApiUrl(): String
}