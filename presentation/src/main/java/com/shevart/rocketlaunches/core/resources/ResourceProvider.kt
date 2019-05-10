package com.shevart.rocketlaunches.core.resources

interface ResourceProvider {
    // Launch status
    val statusLaunchingNowBg: Int
    val statusSuccessfullyBg: Int
    val statusFailedBg: Int
    val statusInFlightBg: Int
    val statusCanceledBg: Int
    val statusScheduledBg: Int
    val statusHoldBg: Int
    val statusUnknownBg: Int
    // Country
    val flagUsa: Int
    val flagRussia: Int
    val flagIndian: Int
    val flagChine: Int
    val flagUnknown: Int
    // Favorite
    val favoriteActive: Int
    val favoriteNonActive: Int
}