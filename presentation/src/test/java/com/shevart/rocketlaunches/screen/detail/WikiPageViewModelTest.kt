package com.shevart.rocketlaunches.screen.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.shevart.domain.models.common.DataWrapper
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.Event.ShowErrorAlert
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.State
import com.shevart.rocketlaunches.screen.util.LAUNCH_ID
import com.shevart.rocketlaunches.screen.util.launch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import io.reactivex.Completable
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
    private val addLaunchToFavoritesUseCase = mock<LaunchesUseCase.AddLaunchToFavorites>()
    private val removeLaunchFromFavoritesUseCase = mock<LaunchesUseCase.RemoveLaunchFromFavorites>()

    @Before
    fun setUp() {
        whenever(getUILaunchByIdUseCase.execute(LAUNCH_ID))
            .thenReturn(Single.just(DataWrapper(launch)))
        whenever(addLaunchToFavoritesUseCase.execute(any()))
            .thenReturn(Completable.complete())
        whenever(removeLaunchFromFavoritesUseCase.execute(any()))
            .thenReturn(Completable.complete())
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

    @Test
    fun `test favorite click - add launch to favorite`() {
        // prepare
        val viewModel = createViewModel()
        viewModel.setLaunchIdParam(LAUNCH_ID)

        // perform
        viewModel.favoriteButtonClick()

        // check
        val state = viewModel.getStateLiveData().value as State
        assertEquals(launch.wikiUrl, state.wikiPageLink)
        assertEquals(true, state.favorite)
        assertEquals(false, state.emptyView)
        verify(addLaunchToFavoritesUseCase, times(1)).execute(LAUNCH_ID)
    }

    @Test
    fun `test favorite click - remove launch from favorite`() {
        // prepare
        whenever(getUILaunchByIdUseCase.execute(LAUNCH_ID))
            .thenReturn(Single.just(DataWrapper(launch.copy(favorite = true))))
        val viewModel = createViewModel()
        viewModel.setLaunchIdParam(LAUNCH_ID)

        // perform
        viewModel.favoriteButtonClick()

        // check
        val state = viewModel.getStateLiveData().value as State
        assertEquals(false, state.favorite)
        assertEquals(launch.wikiUrl, state.wikiPageLink)
        assertEquals(false, state.emptyView)
        verify(removeLaunchFromFavoritesUseCase, times(1)).execute(LAUNCH_ID)
    }

    private fun createViewModel() = WikiPageViewModel(
        getUILaunchByIdUseCase = getUILaunchByIdUseCase,
        addLaunchToFavoritesUseCase = addLaunchToFavoritesUseCase,
        removeLaunchFromFavoritesUseCase = removeLaunchFromFavoritesUseCase
    )
}