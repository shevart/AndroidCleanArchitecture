package com.shevart.rocketlaunches.screen.home.favorites

import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event.OpenLaunchDetail
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State.*
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import java.lang.IllegalStateException
import javax.inject.Inject

class FavoritesViewModel
@Inject constructor(
    private val getFavoritesListUseCase: UILaunchesUseCase.GetUIFavoriteLaunches,
    private val removeLaunchFromFavorites: LaunchesUseCase.RemoveLaunchFromFavorites
) : AbsStateViewModel<State, Event>() {
    init {
        updateState(Loading)
        loadFavoritesList()
    }

    fun openLaunchDetail(launch: UILaunch) {
        sendEvent(OpenLaunchDetail(launchId = launch.id))
    }

    fun removeFromFavorites(launch: UILaunch) {
        removeLaunchFromFavorites.execute(launch.id)
            .subscribe(
                { onLaunchRemoved(launch) },
                this::defaultHandleException
            )
            .addToClearedDisposable()
    }

    private fun loadFavoritesList() {
        getFavoritesListUseCase.execute()
            .subscribe(
                this::updateFavoritesList,
                this::defaultHandleException
            )
            .addToClearedDisposable()
    }

    private fun updateFavoritesList(favorites: List<UILaunch>) {
        if (favorites.isNotEmpty()) {
            updateState(FavoritesList(favorites))
        } else {
            updateState(EmptyFavoritesList)
        }
    }

    private fun onLaunchRemoved(launch: UILaunch) {
        val favorites = (currentState as? FavoritesList)?.favorites?.toMutableList()
            ?: return

        val indexOfRemovedLaunch = favorites.indexOfFirst { it.id == launch.id }
        if (indexOfRemovedLaunch == -1) {
            throw IllegalStateException()
        }
        favorites.removeAt(indexOfRemovedLaunch)
        updateFavoritesList(favorites)
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