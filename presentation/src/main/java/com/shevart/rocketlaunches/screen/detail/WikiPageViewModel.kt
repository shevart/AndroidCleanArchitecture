package com.shevart.rocketlaunches.screen.detail

import com.shevart.rocketlaunches.base.mvvm.AbsStateViewModel
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.Event
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.State
import javax.inject.Inject

class WikiPageViewModel
@Inject constructor() : AbsStateViewModel<State, Event>() {
    init {
        updateState(State())
    }



    data class State(
        val favorite: Boolean = false,
        val wikiPageLink: String = ""
    )

    sealed class Event
}