package com.shevart.domain.models.common

data class DataWrapper<T>(val data: T?) {
    companion object  {
        fun <T> empty() = DataWrapper<T>(null)
    }
}