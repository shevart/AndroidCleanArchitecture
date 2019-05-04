package com.shevart.domain.contract.util

const val DEFAULT_TAG = "RocketLaunchesApp"

interface RLog {
    fun log(tag: String = DEFAULT_TAG, msg: String)

    fun log(e: Throwable)
}