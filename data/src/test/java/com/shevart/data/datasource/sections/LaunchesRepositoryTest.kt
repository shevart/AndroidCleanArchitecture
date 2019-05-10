package com.shevart.data.datasource.sections

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.data.local.LocalDataProvider
import com.shevart.data.models.mapper.LaunchMapper
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.data.util.pageResultWithLaunchesList
import com.shevart.domain.contract.data.PageRequest
import io.reactivex.Single
import org.junit.Before

import org.junit.Test

class LaunchesRepositoryTest {
    private val remoteDataProvider = mock<RemoteDataProvider>()
    private val localDataProvider = mock<LocalDataProvider>()

    private val count = 2
    private val offset = 0

    @Before
    fun setUp() {
    }

    @Test
    fun `test load page - no cache`() {
        // prepare
        whenever(remoteDataProvider.getRocketLaunches(count, offset))
            .thenReturn(Single.just(pageResultWithLaunchesList))
        val repository = createRepository()

        // perform
        val param = PageRequest(offset = offset, count = count)
        val observer = repository.getLaunches(param).test()

        // check
        observer.assertValue { it == pageResultWithLaunchesList }
        verify(remoteDataProvider, times(1)).getRocketLaunches(count, offset)
    }

    @Test
    fun `test load page - with cache`() {
        // prepare
        whenever(remoteDataProvider.getRocketLaunches(count, offset))
            .thenReturn(Single.just(pageResultWithLaunchesList))
        val repository = createRepository()

        // perform
        val param = PageRequest(offset = offset, count = count)
        // -- warm cache
        repository.getLaunches(param).subscribe()
        // -- should be from cache
        val observer = repository.getLaunches(param).test()

        // check
        observer.assertValue { it.offset == pageResultWithLaunchesList.offset }
        observer.assertValue { it.totalCount == pageResultWithLaunchesList.totalCount }
        observer.assertValue { it.items == pageResultWithLaunchesList.items.subList(0, count) }
        verify(remoteDataProvider, times(1)).getRocketLaunches(count, offset)
    }

    private fun createRepository() = LaunchesRepository(
        remoteDataProvider = remoteDataProvider,
        localDataProvider = localDataProvider
    )
}