package com.shevart.domain.usecase.impl

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.domain.contract.app.AppConfigProvider
import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.contract.data.PageRequest
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.util.createRocketLaunchesList
import com.shevart.domain.util.schedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class GetNextLaunchesPageUseCaseTest {
    private val launchesSection = mock<DataSource.LaunchesSection>()
    private val appConfigProvider = mock<AppConfigProvider>()

    private val totalCount = 4
    private val itemsPerPageCount = 2
    private val paramFirstPage = PageRequest(
        offset = 0,
        count = 2
    )
    private val pageResultFirst = PageResult(
        items = createRocketLaunchesList(itemsPerPageCount),
        count = itemsPerPageCount,
        totalCount = totalCount,
        offset = 0
    )

    @Before
    fun setUp() {
        whenever(appConfigProvider.getLaunchesPerPageCount())
            .thenReturn(itemsPerPageCount)
        whenever(launchesSection.getLaunches(paramFirstPage))
            .thenReturn(Single.just(pageResultFirst))
    }

    @Test
    fun `test - first page`() {
        // prepare
        val useCase = createUseCase()

        // perform
        val observer = useCase.execute(0).test()

        // check
        observer.assertValue { it.hasMoreItems }
        observer.assertValue { it.launches == pageResultFirst.items }
    }

    @Test
    fun `test - first page, there is no more items`() {
        // prepare
        whenever(launchesSection.getLaunches(paramFirstPage))
            .thenReturn(Single.just(pageResultFirst.copy(totalCount = 2)))
        val useCase = createUseCase()

        // perform
        val observer = useCase.execute(0).test()

        // check
        observer.assertValue { !it.hasMoreItems }
        observer.assertValue { it.launches == pageResultFirst.items }
    }

    private fun createUseCase() = GetNextLaunchesPageUseCase(
        launchesSection = launchesSection,
        appConfigProvider = appConfigProvider,
        schedulerProvider = schedulerProvider
    )
}