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
            favoritesList.add(launch.copy(favorite = true))
        }
    }

    override fun removeFromFavorites(launchId: Long) = Completable.fromRunnable {
        val favorite = favoritesList.find { it.id == launchId }
        if (favorite != null) {
            favoritesList.remove(favorite)
        }
    }

    override fun getFavorites() = Single.just(favoritesList.toList())

    override fun isFavorite(launchId: Long) =
        favoritesList.find { it.id == launchId } != null
}