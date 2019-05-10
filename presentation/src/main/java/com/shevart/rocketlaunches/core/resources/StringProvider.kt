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

    val countryUSA: Int
    val countryChina: Int
    val countryIndian: Int
    val countryRussia: Int
    val countryUnknown: Int
}