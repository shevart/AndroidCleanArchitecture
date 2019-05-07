package com.shevart.rocketlaunches.screen.home.host

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Screen.FavoritesScreen
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Screen.LaunchesScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainScreenViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `test initial state`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value!!

        // check
        assertEquals(LaunchesScreen, state.screen)
    }

    @Test
    fun `test change screens`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        // -- select favorites
        viewModel.showFavorites()
        val shouldBeFavorites = viewModel.getStateLiveData().value!!.screen
        // -- select launches
        viewModel.showLaunchesScreen()
        val shouldBeLaunches = viewModel.getStateLiveData().value!!.screen

        // check
        assertEquals(LaunchesScreen, shouldBeLaunches)
        assertEquals(FavoritesScreen, shouldBeFavorites)
    }

    private fun createViewModel() = MainScreenViewModel()
}