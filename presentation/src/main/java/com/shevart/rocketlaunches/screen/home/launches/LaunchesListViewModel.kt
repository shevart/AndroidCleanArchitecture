package com.shevart.rocketlaunches.screen.home.launches

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import javax.inject.Inject

class LaunchesListViewModel
@Inject constructor() : AbsStateViewModel<State, Event>() {

    sealed class Event

    sealed class State {
        object Loading : State()

        data class ShowLaunchesList(
            val showBottomListLoadingIndicator: Boolean = false
        ) : State()

        data class Error(val error: Throwable) : State()
    }
}