package com.shevart.rocketlaunches.screen.home.launches

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.Loading
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter.LaunchViewHolder
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LaunchesListViewModelTest {
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
        val state = viewModel.getStateLiveData().value

        // check
        assertEquals(Loading, state)
    }

    private fun createViewModel() = LaunchesListViewModel()
}