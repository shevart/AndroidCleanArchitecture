package com.shevart.rocketlaunches.screen.detail

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.Event
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.Event.ShowErrorAlert
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.State
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.util.checkLaunchId
import javax.inject.Inject

class WikiPageViewModel
@Inject constructor(
    private val getUILaunchByIdUseCase: UILaunchesUseCase.GetUILaunchById
) : AbsStateViewModel<State, Event>() {
    private var launchId = 0L

    init {
        updateState(State())
    }

    /**
     * I know, this is a very bad idea - do it via simple direct method.
     * I think that right way for provide similar data - model layer or DI.
     * But I decided to use the simplest DI version - so...
     * ... there is todo provide this data via DI
     */
    fun setLaunchIdParam(launchId: Long) {
        this.launchId = launchId
        loadLaunch()
    }

    private fun loadLaunch() {
        launchId.checkLaunchId()
        getUILaunchByIdUseCase.execute(launchId)
            .map { it.data!! }
            .subscribe(
                this::onLaunchLoaded,
                this::onLaunchLoadFailed
            )
            .addToClearedDisposable()
    }

    private fun onLaunchLoaded(launch: UILaunch) {
        if (!launch.wikiUrl.isNullOrBlank()) {
            updateState(launch.createState())
        } else {
            sendEvent(createErrorEventForEmptyWikiUrl())
        }
    }

    private fun onLaunchLoadFailed(e: Throwable) {
        defaultHandleException(e)
        sendEvent(e.createErrorEvent())
    }

    data class State(
        val favorite: Boolean = false,
        val wikiPageLink: String = ""
    )

    sealed class Event {
        data class ShowErrorAlert(val reason: Throwable) : Event()
    }

    companion object {
        private fun UILaunch.createState() = State(
            favorite = this.favorite,
            wikiPageLink = this.wikiUrl!!
        )

        private fun createErrorEventForEmptyWikiUrl() = ShowErrorAlert(
            reason = IllegalStateException("The wiki page url is null!")
        )

        private fun Throwable.createErrorEvent() = ShowErrorAlert(
            reason = this
        )
    }
}