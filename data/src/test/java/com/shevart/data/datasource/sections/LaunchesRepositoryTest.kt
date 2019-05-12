package com.shevart.data.datasource.sections

import com.nhaarman.mockitokotlin2.*
import com.shevart.data.local.LocalDataProvider
import com.shevart.data.models.mapper.LaunchMapper
import com.shevart.data.remote.RemoteDataProvider
import com.shevart.data.util.pageResultWithLaunchesList
import com.shevart.domain.contract.data.PageRequest
import com.shevart.domain.util.createRocketLaunch
import io.reactivex.Completable
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
        whenever(localDataProvider.addToFavorites(any()))
            .thenReturn(Completable.complete())
        whenever(localDataProvider.removeFromFavorites(any()))
            .thenReturn(Completable.complete())
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

    @Test
    fun `test favorites - add launch to list`() {
        // prepare
        val launch = createRocketLaunch(1)
        val repository = createRepository()
        val eventsObserver = repository.getLaunchAddedToFavoritesObservable().test()

        // perform
        repository.addLaunchToFavorites(launch)
            .subscribe()

        // check
        eventsObserver.assertValue { it == launch.id }
        verify(localDataProvider, times(1)).addToFavorites(launch)
    }

    @Test
    fun `test favorites - remove launch from list`() {
        // prepare
        val launch = createRocketLaunch(2)
        val repository = createRepository()
        val eventsObserver = repository.getLaunchRemovedFromFavoritesObservable().test()

        // perform
        repository.removeLaunchFromFavorites(launch)
            .subscribe()

        // check
        eventsObserver.assertValue { it == launch.id }
        verify(localDataProvider, times(1)).removeFromFavorites(launch.id)
    }

    private fun createRepository() = LaunchesRepository(
        remoteDataProvider = remoteDataProvider,
        localDataProvider = localDataProvider
    )
}