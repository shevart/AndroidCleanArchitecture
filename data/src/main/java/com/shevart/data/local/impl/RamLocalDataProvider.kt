package com.shevart.data.local.impl

import com.shevart.data.local.LocalDataProvider
import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Save data on RAM memory
 */
class RamLocalDataProvider
@Inject constructor() : LocalDataProvider {
    private val favoritesList = ArrayList<RocketLaunch>()

    override fun addToFavorites(launch: RocketLaunch) = Completable.fromRunnable {
        if (!favoritesList.contains(launch)) {
            favoritesList.add(launch)
        }
    }

    override fun removeFromFavorites(launch: RocketLaunch) = Completable.fromRunnable {
        favoritesList.remove(launch)
    }

    override fun getFavorites() = Single.just(favoritesList.toList())
}