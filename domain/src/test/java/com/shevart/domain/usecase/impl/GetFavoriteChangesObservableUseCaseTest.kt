package com.shevart.domain.usecase.impl

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetFavoriteChangesObservable.FavoriteEvent
import com.shevart.domain.util.schedulerProvider
import io.reactivex.subjects.PublishSubject
import junit.framework.Assert.assertEquals
import org.junit.Before

import org.junit.Test

class GetFavoriteChangesObservableUseCaseTest {
    private val launchesSection = mock<DataSource.LaunchesSection>()

    private val addedLaunchToFavoriteSubject = PublishSubject.create<Long>()
    private val removedLaunchToFavoriteSubject = PublishSubject.create<Long>()

    private val addedLaunchId = 1L
    private val removedLaunchId = 2L

    private val addedEvent = FavoriteEvent(addedLaunchId, FavoriteEvent.Action.Added)
    private val removedEvent = FavoriteEvent(removedLaunchId, FavoriteEvent.Action.Removed)

    @Before
    fun setUp() {
        whenever(launchesSection.getLaunchAddedToFavoritesObservable())
            .thenReturn(addedLaunchToFavoriteSubject)
        whenever(launchesSection.getLaunchRemovedFromFavoritesObservable())
            .thenReturn(removedLaunchToFavoriteSubject)
    }

    @Test
    fun `test - favorite added event`() {
        // prepare
        val useCase = createUseCase()
        val observer = useCase.execute().test()

        // perform
        addedLaunchToFavoriteSubject.onNext(addedLaunchId)

        // check
        observer.assertValue { it == addedEvent }
    }

    @Test
    fun `test - favorite removed event`() {
        // prepare
        val useCase = createUseCase()
        val observer = useCase.execute().test()

        // perform
        removedLaunchToFavoriteSubject.onNext(removedLaunchId)

        // check
        observer.assertValue { it == removedEvent }
    }

    @Test
    fun `test - favorite added and removed event`() {
        // prepare
        val useCase = createUseCase()
        val observer = useCase.execute().test()

        // perform
        addedLaunchToFavoriteSubject.onNext(addedLaunchId)
        removedLaunchToFavoriteSubject.onNext(removedLaunchId)

        // check
        assertEquals(addedEvent, observer.values()[0])
        assertEquals(removedEvent, observer.values()[1])
        assertEquals(2, observer.valueCount())
    }

    private fun createUseCase() = GetFavoriteChangesObservableUseCase(
        launchesSection = launchesSection,
        schedulerProvider = schedulerProvider
    )
}