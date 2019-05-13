package com.shevart.rocketlaunches.screen.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State.EmptyList
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SearchLaunchViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @Test
    fun `test initial test`() {
        // prepare
        val viewModel = createViewModel()

        // perform
        val state = viewModel.getStateLiveData().value

        // check
        assertEquals(EmptyList, state)
    }

    private fun createViewModel() = SearchLaunchViewModel()
}