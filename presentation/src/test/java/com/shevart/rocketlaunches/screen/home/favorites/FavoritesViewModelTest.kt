package com.shevart.rocketlaunches.screen.home.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State.Loading
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

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
        assertEquals(Loading, state)
    }

    private fun createViewModel() = FavoritesViewModel()
}