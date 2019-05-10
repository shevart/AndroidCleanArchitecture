package com.shevart.data.models.mapper

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiLaunchServiceProvider
import com.shevart.data.models.mapper.media.LaunchServiceProviderMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.LaunchServiceProvider
import com.shevart.domain.models.launch.MediaInfo
import javax.inject.Inject
import javax.inject.Named

class LaunchServiceProviderMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_LAUNCH_SERVICE_PROVIDER)
    private val mediaInfoMapper: Mapper<ApiLaunchServiceProvider, MediaInfo>
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