package com.shevart.rocketlaunches.screen.home.launches

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.*
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase.GetNextUILaunchesPage.UIResult
import com.shevart.rocketlaunches.util.plus
import javax.inject.Inject

class LaunchesListViewModel
@Inject constructor(
    private val getNextLaunchesPageUseCase: UILaunchesUseCase.GetNextUILaunchesPage
) : AbsStateViewModel<State, Event>() {
    private var nextPageLoadingNow = false

    init {
        updateState(Loading)
        loadFirstPage()
    }

    fun onListEndReached() {
        val state = currentState as? ShowLaunchesList
        // Why I use named variable for if statement? - for simplify readability.
        // I don't like use methods for such actions because there is one goal for this
        // statement - well readable name of condition. And variable covers it
        val loadNextPage = state?.showBottomListLoadingIndicator == true && !nextPageLoadingNow
        if (loadNextPage) {
            loadNextPage(state!!.launchesItems.size)
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

    sealed class Event

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