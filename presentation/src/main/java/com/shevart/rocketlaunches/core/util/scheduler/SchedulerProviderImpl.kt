package com.shevart.rocketlaunches.core.util.scheduler

import com.shevart.domain.contract.scheduler.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProviderImpl
@Inject constructor() : SchedulerProvider {
    override fun io() = Schedulers.io()

    override fun observe() = AndroidSchedulers.mainThread()!!
}