package com.shevart.rocketlaunches.screen.search

import com.shevart.domain.models.launch.SimplePageResult
import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event.FinishSearching
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event.OpenLaunch
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State.*
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SearchLaunchViewModel
@Inject constructor(
    private val findLaunchesByNameUseCase: UILaunchesUseCase.FindUILaunchesByName
) : AbsStateViewModel<State, Event>() {
    private var nextPageLoadingDisposable: Disposable? = null
    private var name = ""

    init {
        updateState(EmptyList)
    }

    fun cancel() {
        sendEvent(FinishSearching)
    }

    fun openLaunch(launch: UILaunch) {
        sendEvent(OpenLaunch(launch.id))
    }

    fun findLaunches(name: String) {
        this.name = name
        if (name.length < 3) {
            showEmptyList()
            return
        }
        nextPageLoadingDisposable?.dispose()
        nextPageLoadingDisposable = null
        findLaunchesByNameUseCase.execute(name, showedItems = 0)
            .subscribe(
                this::onFindLaunchesResult,
                this::onLaunchSearchError
            )
            .addToClearedDisposable()
    }

    fun onListEndReached() {
        val state = currentState as? ShowFoundLaunches
        val loadNextPage = state?.showBottomProgress == true &&
                nextPageLoadingDisposable == null
        if (loadNextPage) {
            loadNextPage(name, state!!.launches.size)
        }
    }

    private fun loadNextPage(name: String, showedItems: Int) {
        nextPageLoadingDisposable = findLaunchesByNameUseCase
            .execute(name, showedItems)
            .doOnError { nextPageLoadingDisposable = null }
            .subscribe(
                this::onNextPageLoaded,
                this::onLaunchSearchError
            )
            .addToClearedDisposable()
    }

    private fun onNextPageLoaded(result: SimplePageResult<UILaunch>) {
        nextPageLoadingDisposable = null
        val state = currentState as? ShowFoundLaunches
        if (state == null) {
            onFindLaunchesResult(result)
            return
        }
        updateState(state.addPage(result))
    }

    private fun onLaunchSearchError(e: Throwable) {
        defaultHandleException(e)
        updateState(LaunchesNotFound)
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
        data class OpenLaunch(val launchId: Long) : Event()
        object FinishSearching : Event()
    }

    private companion object {
        fun SimplePageResult<UILaunch>.toShowFoundLaunchesState() =
            ShowFoundLaunches(
                launches = this.launches,
                showBottomProgress = this.hasMoreItems
            )

        fun ShowFoundLaunches.addPage(pageResult: SimplePageResult<UILaunch>) = this.copy(
            launches = this.launches.plus(pageResult.launches),
            showBottomProgress = pageResult.hasMoreItems
        )
    }
}