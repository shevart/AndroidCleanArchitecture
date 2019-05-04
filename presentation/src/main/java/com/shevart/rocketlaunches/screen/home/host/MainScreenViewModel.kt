package com.shevart.rocketlaunches.screen.home.host

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Event
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Screen.FavoritesScreen
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Screen.LaunchesScreen
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.State
import javax.inject.Inject

class MainScreenViewModel
@Inject constructor() : AbsStateViewModel<State, Event>() {
    init {
        updateState(State(screen = LaunchesScreen))
    }

    fun showLaunchesScreen() {
        updateState(currentState.copy(screen = LaunchesScreen))
    }

    fun showFavorites() {
        updateState(currentState.copy(screen = FavoritesScreen))
    }

    sealed class Event

    data class State(val screen: Screen)

    sealed class Screen {
        object LaunchesScreen : Screen()
        object FavoritesScreen : Screen()
    }
}