package com.shevart.rocketlaunches.screen.home.launches

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.models.UILaunchStatus
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event.OpenLaunchDetail
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.Loading
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter.LaunchViewHolder
import com.shevart.rocketlaunches.screen.util.launch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LaunchesListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val getNextLaunchesPageUseCase = mock<UILaunchesUseCase.GetNextUILaunchesPage>()

    @Before
    fun setUp() {
        whenever(getNextLaunchesPageUseCase.execute(0)).thenReturn(Single.never())
    }

    @Test
    fun `test initial state`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value

        // check
        assertEquals(Loading, state)
    }

    @Test
    fun `test open launch detail`() {
        // prepare
        val viewModel = createViewModel()
        val eventsObserver = viewModel.getEventsObservable().test()

        // perform
        viewModel.openLaunchDetail(launch)

        // check
        val event = eventsObserver.values().first() as OpenLaunchDetail
        assertEquals(launch.id, event.launchId)
        eventsObserver.assertNoErrors()
    }

    private fun createViewModel() = LaunchesListViewModel(
        getNextLaunchesPageUseCase = getNextLaunchesPageUseCase
    )
}