package com.shevart.domain.util

import com.shevart.domain.contract.scheduler.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Single<T>.subscribeOnIoObserveOnMain(schedulerProvider: SchedulerProvider) = this
    .subscribeOn(schedulerProvider.io())
    .observeOn(schedulerProvider.observe())

fun <T> Observable<T>.subscribeOnIoObserveOnMain(schedulerProvider: SchedulerProvider) = this
    .subscribeOn(schedulerProvider.io())
    .observeOn(schedulerProvider.observe())!!

fun Completable.subscribeOnIoObserveOnMain(schedulerProvider: SchedulerProvider) = this
    .subscribeOn(schedulerProvider.io())
    .observeOn(schedulerProvider.observe())