package com.shevart.rocketlaunches.base.mvvm

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class AbsStateViewModel<State, Event> : BaseViewModel() {
    private val stateLiveData = MutableLiveData<State>()
    private val eventsSubject = PublishSubject.create<Event>()
    protected val currentState: State
        get() = stateLiveData.value!!

    fun getStateLiveData(): LiveData<State> = stateLiveData

    fun getEventsObservable(): Observable<Event> = eventsSubject

    @MainThread
    fun updateState(state: State) {
        stateLiveData.value = state
    }

    fun updateStatePost(state: State) {
        stateLiveData.postValue(state)
    }
}