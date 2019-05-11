package com.shevart.rocketlaunches.screen.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.domain.models.common.DataWrapper
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.Event.ShowErrorAlert
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.State
import com.shevart.rocketlaunches.screen.util.LAUNCH_ID
import com.shevart.rocketlaunches.screen.util.launch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class WikiPageViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val getUILaunchByIdUseCase = mock<UILaunchesUseCase.GetUILaunchById>()

    @Before
    fun setUp() {
    }

    @Test
    fun `test initial state`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value as State
        // check
        assertEquals("", state.wikiPageLink)
        assertEquals(false, state.favorite)
        assertEquals(false, state.emptyView)
    }

    @Test
    fun `test - load wiki`() {
        // prepare
        whenever(getUILaunchByIdUseCase.execute(LAUNCH_ID))
            .thenReturn(Single.just(DataWrapper(launch)))
        val viewModel = createViewModel()

        // perform
        viewModel.setLaunchIdParam(LAUNCH_ID)

        // check
        val state = viewModel.getStateLiveData().value as State
        assertEquals(launch.wikiUrl, state.wikiPageLink)
        assertEquals(launch.favorite, state.favorite)
        assertEquals(false, state.emptyView)
    }

    @Test
    fun `test - load wiki - for empty wiki page`() {
        // prepare
        whenever(getUILaunchByIdUseCase.execute(LAUNCH_ID))
            .thenReturn(Single.just(DataWrapper(launch.copy(wikiUrl = ""))))
        val viewModel = createViewModel()

        // perform
        viewModel.setLaunchIdParam(LAUNCH_ID)

        // check
        val state = viewModel.getStateLiveData().value as State
        assertEquals(true, state.emptyView)
        assertEquals("", state.wikiPageLink)
        assertEquals(false, state.favorite)
    }

    @Test
    fun `test - load wiki - loading failed`() {
        // prepare
        whenever(getUILaunchByIdUseCase.execute(LAUNCH_ID))
            .thenReturn(Single.error(RuntimeException("Mock!")))
        val viewModel = createViewModel()
        val eventsObserver = viewModel.getEventsObservable().test()

        // perform
        viewModel.setLaunchIdParam(LAUNCH_ID)

        // check
        val errorEvent = eventsObserver.values().first() as ShowErrorAlert
        assertEquals(RuntimeException::class.java, errorEvent.reason::class.java)
        eventsObserver.assertNoErrors()
    }

    private fun createViewModel() = WikiPageViewModel(
        getUILaunchByIdUseCase = getUILaunchByIdUseCase
    )
}