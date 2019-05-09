package com.shevart.data.models.mapper

import com.shevart.data.models.ApiLaunchServiceProvider
import com.shevart.data.models.mapper.media.LaunchServiceProviderMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.LaunchServiceProvider
import com.shevart.domain.models.launch.MediaInfo

class LaunchServiceProviderMapper(
    private val mediaInfoMapper: Mapper<ApiLaunchServiceProvider, MediaInfo> = LaunchServiceProviderMediaInfoMapper()
) : Mapper<ApiLaunchServiceProvider, LaunchServiceProvider>() {
    override fun map(from: ApiLaunchServiceProvider) =
        LaunchServiceProvider(
            id = from.id,
            name = from.name,
            abbreviation = from.abbrev,
            countryCode = from.countryCode,
            type = from.type,
            mediaInfo = mediaInfoMapper.map(from)
        )
}