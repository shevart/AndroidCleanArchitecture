package com.shevart.rocketlaunches.screen.search

import com.shevart.domain.models.launch.SimplePageResult
import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event.FinishSearching
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State.*
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.util.checkLaunchNameForSearch
import javax.inject.Inject

class SearchLaunchViewModel
@Inject constructor(
    private val findLaunchesByNameUseCase: UILaunchesUseCase.FindUILaunchesByName
) : AbsStateViewModel<State, Event>() {

    init {
        updateState(EmptyList)
    }

    fun cancel() {
        sendEvent(FinishSearching)
    }

    fun findLaunches(name: String) {
        if (name.length < 3) {
            showEmptyList()
            return
        }
        findLaunchesByNameUseCase.execute(name, showedItems = 0)
            .subscribe(
                this::onFindLaunchesResult,
                this::defaultHandleException
            )
            .addToClearedDisposable()
    }

    private fun showEmptyList() {
        if (currentState != EmptyList) {
            updateState(EmptyList)
        }
    }

    private fun onFindLaunchesResult(result: SimplePageResult<UILaunch>) {
        if (result.launches.isNotEmpty()) {
            updateState(result.toShowFoundLaunchesState())
        } else {
            updateState(LaunchesNotFound)
        }
    }

    sealed class State {
        object EmptyList : State()
        object LaunchesNotFound : State()
        data class ShowFoundLaunches(
            val launches: List<UILaunch>,
            val showBottomProgress: Boolean = false
        ) : State()
    }

    sealed class Event {
        object FinishSearching : Event()
    }

    private companion object {
        fun SimplePageResult<UILaunch>.toShowFoundLaunchesState() =
            ShowFoundLaunches(
                launches = this.launches,
                showBottomProgress = this.hasMoreItems
            )
    }
}