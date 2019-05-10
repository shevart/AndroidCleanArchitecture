package com.shevart.rocketlaunches.models.mapper

import com.shevart.domain.contract.app.LaunchesList.DEFAULT_LIST_IMAGE_RESOLUTION
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.LaunchStatus
import com.shevart.domain.models.launch.MediaInfo
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.rocketlaunches.core.resources.ResourceProvider
import com.shevart.rocketlaunches.core.resources.StringProvider
import com.shevart.rocketlaunches.di.name.UIMapperNames
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.models.UILaunchStatus
import com.shevart.rocketlaunches.util.ui.getCountryFlagResId
import com.shevart.rocketlaunches.util.ui.getCountryNameResId
import com.shevart.rocketlaunches.util.ui.getFavoriteResId
import javax.inject.Inject
import javax.inject.Named

class UILaunchMapper
@Inject constructor(
    private val stringProvider: StringProvider,
    private val resourceProvider: ResourceProvider,
    @Named(UIMapperNames.UI_MAPPER_LAUNCH_STATUS)
    private val launchStatusMapper: Mapper<LaunchStatus, UILaunchStatus>
) : Mapper<RocketLaunch, UILaunch>() {
    override fun map(from: RocketLaunch) =
        UILaunch(
            id = from.id,
            name = from.name,
            countryFlagResId = resourceProvider.getCountryFlagResId(from.country),
            countryNameResId = stringProvider.getCountryNameResId(from.country),
            favoritesIconResId = resourceProvider.getFavoriteResId(from.favorite),
            date = from.date.toString(), // todo map date
            status = launchStatusMapper.map(from.status),
            imageUrl = from.rocket.mediaInfo.obtainImageUrl()
        )

    /**
     * This is bad idea to put logic of image selection to mapper.
     * But if have no time for do it in more right way. I think this work must do viewModel or
     * useCase
     *
     * So there is todo - replace this logic to right place
     */
    private fun MediaInfo.obtainImageUrl(): String? {
        return images.find { candidate ->
            candidate.resolution == DEFAULT_LIST_IMAGE_RESOLUTION
                    && !candidate.imageUrl.contains("placeholder")
        }?.imageUrl
    }
}