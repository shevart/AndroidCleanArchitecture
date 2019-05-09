package com.shevart.data.util

import okhttp3.Interceptor
import okhttp3.OkHttpClient

inline fun OkHttpClient.Builder.addNetworkInterceptorByPredicate(
    predicate: Boolean,
    interceptor: () -> Interceptor
) = this.apply {
    if (predicate) {
        addNetworkInterceptor(interceptor())
    }
}