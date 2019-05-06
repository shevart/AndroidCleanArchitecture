package com.shevart.domain.models.error

sealed class LoadDataError {
    object NoInternet: LoadDataError()
    object ServerNotResponding : LoadDataError()
    data class UnknownError(val e: Throwable): LoadDataError()
}