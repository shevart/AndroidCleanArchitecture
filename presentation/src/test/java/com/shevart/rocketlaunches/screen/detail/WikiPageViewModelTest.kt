package com.shevart.rocketlaunches.screen.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.State
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
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
        assertEquals(false, state.favorite)
        assertEquals("", state.wikiPageLink)
    }

    private fun createViewModel() = WikiPageViewModel(
        getUILaunchByIdUseCase = getUILaunchByIdUseCase
    )
}