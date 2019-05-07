package com.shevart.rocketlaunches.core.app

import com.shevart.domain.contract.app.AppConfigProvider
import com.shevart.domain.contract.app.LaunchesList.COUNT_OF_LAUNCHES_PER_PAGE
import javax.inject.Inject

class AppConfigProviderImpl
@Inject constructor() : AppConfigProvider {
    override fun getLaunchesPerPageCount() = COUNT_OF_LAUNCHES_PER_PAGE
}