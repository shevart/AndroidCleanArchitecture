package com.shevart.data.models.mapper.media

import com.shevart.data.models.ApiLaunchServiceProvider
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.MediaInfo

class LaunchServiceProviderMediaInfoMapper : Mapper<ApiLaunchServiceProvider, MediaInfo>() {
    override fun map(from: ApiLaunchServiceProvider) =
        MediaInfo(
            infoLink = from.infoUrl ?: "",
            wikiLink = from.wikiUrl ?: "",
            infoLinks = from.infoUrls
        )
}