package com.shevart.rocketlaunches.screen.home.launches

import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetFavoriteChangesObservable.FavoriteEvent
import com.shevart.domain.usecase.impl.GetFavoriteChangesObservableUseCase
import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event.OpenLaunchDetail
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.*
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase.GetNextUILaunchesPage.UIResult
import com.shevart.rocketlaunches.util.plus
import io.reactivex.Completable
import javax.inject.Inject

class LaunchesListViewModel
@Inject constructor(
    private val getNextLaunchesPageUseCase: UILaunchesUseCase.GetNextUILaunchesPage,
    private val addLaunchToFavoritesUseCase: LaunchesUseCase.AddLaunchToFavorites,
    private val removeLaunchFromFavoritesUseCase: LaunchesUseCase.RemoveLaunchFromFavorites,
    private val updateUILaunchFavoriteFieldUseCase: UILaunchesUseCase.UpdateUILaunchFavoriteField,
    private val getFavoritesChangesObservableUseCase: LaunchesUseCase.GetFavoriteChangesObservable
) : AbsStateViewModel<State, Event>() {
    private var nextPageLoadingNow = false

    init {
        updateState(Loading)
        loadFirstPage()
        observeFavoritesChanges()
    }

    fun onListEndReached() {
        val state = currentState as? ShowLaunchesList
        // Why I use named variable for if statement? - for simplify readability.
        // I don't like use methods for such actions because there is one goal for this
        // statement - well readable name of condition. And variable covers it well
        val loadNextPage = state?.showBottomListLoadingIndicator == true && !nextPageLoadingNow
        if (loadNextPage) {
            loadNextPage(state!!.launchesItems.size)
        }
    }

    fun openLaunchDetail(launch: UILaunch) {
        sendEvent(OpenLaunchDetail(launchId = launch.id))
    }

    fun favoriteButtonClick(launch: UILaunch) {
        if (launch.favorite) {
            removeLaunchFromFavorites(launch.id)
        } else {
            addLaunchToFavorites(launch.id)
        }
    }

    private fun loadFirstPage() {
        getNextLaunchesPageUseCase.execute(showedItems = 0)
            .subscribe(
                this::onFirstPageLoaded,
                this::onPageLoadingFailed
            )
            .addToClearedDisposable()
    }

    private fun onFirstPageLoaded(pageResult: UIResult) {
        updateState(stateForFirstLoadedPage(pageResult))
    }

    private fun loadNextPage(showedItems: Int) {
        nextPageLoadingNow = true
        getNextLaunchesPageUseCase.execute(showedItems)
            .subscribe(
                this::onNextPageLoaded,
                this::onPageLoadingFailed
            )
            .addToClearedDisposable()
    }

    private fun onNextPageLoaded(pageResult: UIResult) {
        nextPageLoadingNow = false
        val state = currentState as? ShowLaunchesList
        if (state == null) {
            // interesting case
            onFirstPageLoaded(pageResult)
            return
        }
        updateState(state.addPage(pageResult))
    }

    private fun onPageLoadingFailed(e: Throwable) {
        nextPageLoadingNow = false
        updateState(Error(e))
        defaultHandleException(e)
    }

    private fun observeFavoritesChanges() {
        getFavoritesChangesObservableUseCase.execute()
            .subscribe(
                this::onFavoritesListChange,
                this::defaultHandleException
            )
            .addToClearedDisposable()
    }

    private fun onFavoritesListChange(event: FavoriteEvent) {
        updateLaunchFavoriteFieldInState(
            launchId = event.launchId,
            favorite = event.action == FavoriteEvent.Action.Added
        )
    }

    private fun addLaunchToFavorites(launchId: Long) {
        updateLaunchFavoriteField(
            launchId = launchId,
            updateLaunchRequest = addLaunchToFavoritesUseCase.execute(launchId),
            successAction = this::markLaunchAsFavorite,
            failureAction = this::markLaunchAsNotFavorite
        )
    }

    private fun removeLaunchFromFavorites(launchId: Long) {
        updateLaunchFavoriteField(
            launchId = launchId,
            updateLaunchRequest = removeLaunchFromFavoritesUseCase.execute(launchId),
            successAction = this::markLaunchAsNotFavorite,
            failureAction = this::markLaunchAsFavorite
        )
    }

    private fun updateLaunchFavoriteField(
        launchId: Long,
        updateLaunchRequest: Completable,
        successAction: (launchId: Long) -> Unit,
        failureAction: (launchId: Long) -> Unit
    ) {
        // update launch on UI before request
        successAction(launchId)
        // proceed update launch request
        updateLaunchRequest
            .doOnError { failureAction(launchId) }
            .subscribe(
                {},// do nothing, launch already updated on UI
                this::defaultHandleException
            )
            .addToClearedDisposable()
    }

    private fun markLaunchAsFavorite(launchId: Long) {
        updateLaunchFavoriteFieldInState(
            launchId = launchId,
            favorite = true
        )
    }

    private fun markLaunchAsNotFavorite(launchId: Long) {
        updateLaunchFavoriteFieldInState(
            launchId = launchId,
            favorite = false
        )
    }

    private fun updateLaunchFavoriteFieldInState(launchId: Long, favorite: Boolean) {
        val state = currentState as? ShowLaunchesList
            ?: return
        // find launch
        val launches = state.launchesItems.toMutableList()
        val launch = launches.find { it.id == launchId }
        if (launch == null || launch.favorite == favorite) {
            return
        }
        // update launch favorite field
        val launchPosition = launches.indexOf(launch)
        launches[launchPosition] = updateLaunchFavoriteUI(launch, favorite)
        // update state
        updateState(state.copy(launchesItems = launches))
    }

    private fun updateLaunchFavoriteUI(launch: UILaunch, favorite: Boolean) =
        updateUILaunchFavoriteFieldUseCase.execute(launch, favorite)

    sealed class Event {
        data class OpenLaunchDetail(val launchId: Long) : Event()
    }

    sealed class State {
        object Loading : State()

        data class ShowLaunchesList(
            val showBottomListLoadingIndicator: Boolean = false,
            val launchesItems: List<UILaunch> = emptyList()
        ) : State()

        data class Error(val error: Throwable) : State()
    }

    private companion object {
        fun stateForFirstLoadedPage(pageResult: UIResult) =
            ShowLaunchesList(
                launchesItems = pageResult.launches,
                showBottomListLoadingIndicator = pageResult.hasMoreItems
            )

        fun ShowLaunchesList.addPage(pageResult: UIResult) = this.copy(
            launchesItems = this.launchesItems.plus(pageResult.launches),
            showBottomListLoadingIndicator = pageResult.hasMoreItems
        )
    }
}