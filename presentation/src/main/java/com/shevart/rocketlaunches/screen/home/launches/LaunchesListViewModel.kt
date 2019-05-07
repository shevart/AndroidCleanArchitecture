package com.shevart.rocketlaunches.screen.home.launches

import com.shevart.domain.models.error.LoadDataError
import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.Loading
import javax.inject.Inject

class LaunchesListViewModel
@Inject constructor(

) : AbsStateViewModel<State, Event>() {
    init {
        updateState(Loading)
    }

    sealed class Event

    sealed class State {
        object Loading : State()

        data class ShowLaunchesList(
            val showBottomListLoadingIndicator: Boolean = false,
            val launchesItems: List<UILaunch> = emptyList()
        ) : State()

        data class Error(
            val error: Throwable,
            val loadDataError: LoadDataError? = null
        ) : State()
    }
}