package com.shevart.rocketlaunches.screen.home.favorites

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event.OpenLaunchDetail
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State.*
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import javax.inject.Inject

class FavoritesViewModel
@Inject constructor(
    private val getFavoritesListUseCase: UILaunchesUseCase.GetUIFavoriteLaunches
) : AbsStateViewModel<State, Event>() {
    init {
        updateState(Loading)
        loadFavoritesList()
    }

    fun openLaunchDetail(launch: UILaunch) {
        sendEvent(OpenLaunchDetail(launchId = launch.id))
    }

    private fun loadFavoritesList() {
        getFavoritesListUseCase.execute()
            .subscribe(
                this::onFavoritesListLoaded,
                this::defaultHandleException
            )
            .addToClearedDisposable()
    }

    private fun onFavoritesListLoaded(favorites: List<UILaunch>) {
        if (favorites.isNotEmpty()) {
            updateState(FavoritesList(favorites))
        } else {
            updateState(EmptyFavoritesList)
        }
    }

    sealed class State {
        object Loading : State()
        object EmptyFavoritesList : State()
        data class FavoritesList(val favorites: List<UILaunch>) : State()
    }

    sealed class Event {
        data class OpenLaunchDetail(val launchId: Long) : Event()
    }
}