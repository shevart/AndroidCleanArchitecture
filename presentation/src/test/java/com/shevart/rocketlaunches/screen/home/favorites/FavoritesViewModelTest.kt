package com.shevart.rocketlaunches.screen.home.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetFavoriteChangesObservable.FavoriteEvent
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetFavoriteChangesObservable.FavoriteEvent.Action.Added
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetFavoriteChangesObservable.FavoriteEvent.Action.Removed
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event.OpenLaunchDetail
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State.*
import com.shevart.rocketlaunches.screen.util.launchesList
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val getFavoritesListUseCase = mock<UILaunchesUseCase.GetUIFavoriteLaunches>()
    private val removeLaunchFromFavorites = mock<LaunchesUseCase.RemoveLaunchFromFavorites>()
    private val getFavoritesChangesObservableUseCase =
        mock<LaunchesUseCase.GetFavoriteChangesObservable>()

    @Before
    fun setUp() {
        whenever(getFavoritesListUseCase.execute())
            .thenReturn(Single.just(launchesList))
    }

    @Test
    fun `test initial state - loading`() {
        // prepare
        whenever(getFavoritesListUseCase.execute()).thenReturn(Single.never())
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value as State

        // check
        assertEquals(Loading, state)
        verify(getFavoritesListUseCase, times(1)).execute()
    }

    @Test
    fun `test initial state - favorites list empty`() {
        // prepare
        whenever(getFavoritesListUseCase.execute()).thenReturn(Single.just(emptyList()))
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value as State

        // check
        assertEquals(EmptyFavoritesList, state)
        verify(getFavoritesListUseCase, times(1)).execute()
    }

    @Test
    fun `test initial state - favorites list`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value as State

        // check
        assertEquals(FavoritesList(launchesList), state)
        verify(getFavoritesListUseCase, times(1)).execute()
    }

    @Test
    fun `test click - open launch detail`() {
        // prepare
        val selectedLaunch = launchesList.first()
        val viewModel = createViewModel()
        val eventsObserver = viewModel.getEventsObservable().test()

        // perform
        viewModel.openLaunchDetail(selectedLaunch)

        // check
        eventsObserver.assertValue { it == OpenLaunchDetail(selectedLaunch.id) }
        eventsObserver.assertNoErrors()
    }

    @Test
    fun `test click - remove launch from favorites`() {
        // prepare
        val selectedLaunch = launchesList.first()
        whenever(removeLaunchFromFavorites.execute(selectedLaunch.id))
            .thenReturn(Completable.complete())
        val viewModel = createViewModel()

        // perform
        viewModel.removeFromFavorites(selectedLaunch)

        // check
        val state = viewModel.getStateLiveData().value as FavoritesList
        assertEquals(false, state.favorites.contains(selectedLaunch))
    }

    @Test
    fun `test click - remove launch from favorites - last item`() {
        // prepare
        val selectedLaunch = launchesList.first()
        whenever(getFavoritesListUseCase.execute()).thenReturn(Single.just(listOf(selectedLaunch)))
        whenever(removeLaunchFromFavorites.execute(selectedLaunch.id))
            .thenReturn(Completable.complete())
        val viewModel = createViewModel()

        // perform
        viewModel.removeFromFavorites(selectedLaunch)

        // check
        val state = viewModel.getStateLiveData().value
        assertEquals(EmptyFavoritesList, state)
    }

    @Test
    fun `test favorites list changes - launch was removed from favorites`() {
        // prepare
        val eventsSubject = PublishSubject.create<FavoriteEvent>()
        whenever(getFavoritesChangesObservableUseCase.execute()).thenReturn(eventsSubject)
        val removedLaunchId = launchesList.first().id
        val event = FavoriteEvent(
            launchId = removedLaunchId,
            action = Removed
        )
        val viewModel = createViewModel()

        // perform
        eventsSubject.onNext(event)

        // check
        val state = viewModel.getStateLiveData().value as FavoritesList
        val launchFromStateList = state.favorites.find { it.id == removedLaunchId }
        assertEquals(null, launchFromStateList)
    }

    private fun createViewModel() = FavoritesViewModel(
        getFavoritesListUseCase = getFavoritesListUseCase,
        removeLaunchFromFavorites = removeLaunchFromFavorites,
        getFavoritesChangesObservableUseCase = getFavoritesChangesObservableUseCase
    )
}