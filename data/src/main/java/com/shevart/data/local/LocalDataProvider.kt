package com.shevart.data.local

import com.shevart.domain.models.launch.RocketLaunch
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataProvider {
    fun addToFavorites(launch: RocketLaunch): Completable

    fun removeFromFavorites(launchId: Long): Completable

    fun getFavorites(): Single<List<RocketLaunch>>

    fun isFavorite(launchId: Long): Boolean
}