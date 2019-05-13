package com.shevart.rocketlaunches.screen.search

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State.EmptyList
import javax.inject.Inject

class SearchLaunchViewModel
@Inject constructor(

) : AbsStateViewModel<State, Event>() {

    init {
        updateState(EmptyList)
    }

    sealed class State {
        object EmptyList : State()
        object LaunchesNotFound : State()
        data class ShowFoundLaunches(val launches: UILaunch) : State()
    }

    sealed class Event
}