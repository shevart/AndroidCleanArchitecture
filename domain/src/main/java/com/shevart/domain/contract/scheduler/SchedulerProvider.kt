package com.shevart.domain.contract.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler

    fun observe(): Scheduler
}