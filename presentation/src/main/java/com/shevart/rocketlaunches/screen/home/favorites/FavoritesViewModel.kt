package com.shevart.rocketlaunches.screen.home.favorites

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State.Loading
import javax.inject.Inject

class FavoritesViewModel
@Inject constructor() : AbsStateViewModel<State, Event>() {
    init {
        updateState(Loading)
    }

    sealed class State {
        object Loading : State()
        object EmptyFavoritesList : State()
        data class FavoritesList(val favorites: List<UILaunch>) : State()
    }

    sealed class Event
}