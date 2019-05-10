package com.shevart.rocketlaunches.core.resources

interface ResourceProvider {
    val statusLaunchingNowBg: Int
    val statusSuccessfullyBg: Int
    val statusFailedBg: Int
    val statusInFlightBg: Int
    val statusCanceledBg: Int
    val statusScheduledBg: Int
    val statusHoldBg: Int
    val statusUnknownBg: Int
}