package com.shevart.rocketlaunches.screen.home.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State.*
import com.shevart.rocketlaunches.screen.util.launchesList
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val getFavoritesListUseCase = mock<UILaunchesUseCase.GetUIFavoriteLaunches>()

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

    private fun createViewModel() = FavoritesViewModel(
        getFavoritesListUseCase = getFavoritesListUseCase
    )
}