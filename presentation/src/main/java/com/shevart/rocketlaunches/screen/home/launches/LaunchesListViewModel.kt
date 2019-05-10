package com.shevart.rocketlaunches.screen.home.launches

import com.shevart.domain.models.error.LoadDataError
import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.Loading
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.ShowLaunchesList
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase.GetNextUILaunchesPage.UIResult
import javax.inject.Inject

class LaunchesListViewModel
@Inject constructor(
    private val getNextLaunchesPageUseCase: UILaunchesUseCase.GetNextUILaunchesPage
) : AbsStateViewModel<State, Event>() {
    init {
        updateState(Loading)
        loadFirstPage()
    }

    private fun loadFirstPage() {
        getNextLaunchesPageUseCase.execute(0)
            .subscribe(
                this::onPageLoaded,
                this::onPageLoadingFailed
            )
            .addToClearedDisposable()
    }

    private fun onPageLoaded(pageResult: UIResult) {
        updateState(stateForFirstLoadedPage(pageResult))
    }

    private fun onPageLoadingFailed(e: Throwable) {
        // todo show error
        defaultHandleException(e)
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

    private companion object {
        fun stateForFirstLoadedPage(pageResult: UIResult) =
            ShowLaunchesList(
                launchesItems = pageResult.launches,
                showBottomListLoadingIndicator = pageResult.hasMoreItems
            )
    }
}