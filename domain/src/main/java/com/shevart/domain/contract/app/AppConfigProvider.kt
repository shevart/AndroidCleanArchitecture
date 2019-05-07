package com.shevart.domain.contract.app

interface AppConfigProvider {
    fun getLaunchesPerPageCount(): Int
}