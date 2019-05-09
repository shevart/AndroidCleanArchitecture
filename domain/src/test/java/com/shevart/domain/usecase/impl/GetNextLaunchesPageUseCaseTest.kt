package com.shevart.domain.usecase.impl

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.domain.contract.app.AppConfigProvider
import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.util.schedulerProvider
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class GetNextLaunchesPageUseCaseTest {
    private val launchesSection = mock<DataSource.LaunchesSection>()
    private val appConfigProvider = mock<AppConfigProvider>()

    private val itemsPerPageCount = 2

    @Before
    fun setUp() {
        whenever(appConfigProvider.getLaunchesPerPageCount()).thenReturn(itemsPerPageCount)
    }

    @Test
    fun `test - `() {
        // prepare

        // perform

        // check
    }

    private fun createUseCase() = GetNextLaunchesPageUseCase(
        launchesSection = launchesSection,
        appConfigProvider = appConfigProvider,
        schedulerProvider = schedulerProvider
    )
}