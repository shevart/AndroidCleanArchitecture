package com.shevart.domain.usecase.impl

import com.shevart.domain.contract.data.DataSource
import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.usecase.contract.LaunchesUseCase
import com.shevart.domain.usecase.contract.LaunchesUseCase.GetFavoriteChangesObservable.Event
import com.shevart.domain.util.subscribeOnIoObserveOnMain
import io.reactivex.Observable
import javax.inject.Inject

class GetFavoriteChangesObservableUseCase
@Inject constructor(
    private val launchesSection: DataSource.LaunchesSection,
    private val schedulerProvider: SchedulerProvider
) : LaunchesUseCase.GetFavoriteChangesObservable {
    override fun execute() =
        Observable
            .merge(observeAddedEvents(), observeRemovedEvents())
            .subscribeOnIoObserveOnMain(schedulerProvider)

    private fun observeAddedEvents() =
        launchesSection
            .getLaunchAddedToFavoritesObservable()
            .map(this::createLaunchAddedToFavoritesEvent)

    private fun observeRemovedEvents() =
        launchesSection
            .getLaunchRemovedFromFavoritesObservable()
            .map(this::createLaunchRemovedToFavoritesEvent)

    private fun createLaunchAddedToFavoritesEvent(launchId: Long) = Event(
        launchId = launchId,
        action = Event.Action.Added
    )

    private fun createLaunchRemovedToFavoritesEvent(launchId: Long) = Event(
        launchId = launchId,
        action = Event.Action.Removed
    )
}