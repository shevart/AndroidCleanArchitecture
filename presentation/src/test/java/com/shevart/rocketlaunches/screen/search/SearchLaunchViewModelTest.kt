package com.shevart.rocketlaunches.screen.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State.EmptyList
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SearchLaunchViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val findLaunchesByNameUseCase = mock<UILaunchesUseCase.FindUILaunchesByName>()

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

    private fun createViewModel() = SearchLaunchViewModel(
        findLaunchesByNameUseCase = findLaunchesByNameUseCase
    )
}