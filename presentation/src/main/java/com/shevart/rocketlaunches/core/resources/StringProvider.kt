package com.shevart.rocketlaunches.core.resources

interface StringProvider {
    val statusLaunchingNow: Int
    val statusSuccessfully: Int
    val statusFailed: Int
    val statusInFlight: Int
    val statusCanceled: Int
    val statusScheduled: Int
    val statusHold: Int
    val statusUnknown: Int
}