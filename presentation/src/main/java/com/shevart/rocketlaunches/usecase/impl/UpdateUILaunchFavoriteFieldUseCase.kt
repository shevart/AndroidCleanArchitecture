package com.shevart.rocketlaunches.usecase.impl

import com.shevart.rocketlaunches.core.resources.ResourceProvider
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.usecase.UILaunchesUseCase
import com.shevart.rocketlaunches.util.ui.getFavoriteResId
import javax.inject.Inject

class UpdateUILaunchFavoriteFieldUseCase
@Inject constructor(
    private val resourceProvider: ResourceProvider
) : UILaunchesUseCase.UpdateUILaunchFavoriteField {
    override fun execute(launch: UILaunch, favorite: Boolean): UILaunch {
        return launch.copy(
            favorite = favorite,
            favoritesIconResId = resourceProvider.getFavoriteResId(favorite)
        )
    }
}