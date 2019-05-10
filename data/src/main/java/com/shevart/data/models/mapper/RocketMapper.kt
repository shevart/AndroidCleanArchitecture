package com.shevart.data.models.mapper

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiAgency
import com.shevart.data.models.ApiRocket
import com.shevart.data.models.mapper.media.RocketMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Agency
import com.shevart.domain.models.launch.MediaInfo
import com.shevart.domain.models.launch.Rocket
import javax.inject.Inject
import javax.inject.Named

class RocketMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_AGENCY)
    private val agencyMapper: Mapper<ApiAgency, Agency>,
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_ROCKET)
    private val rocketMediaInfoMapper: Mapper<ApiRocket, MediaInfo>
) : Mapper<ApiRocket, Rocket>() {
    override fun map(from: ApiRocket) =
        Rocket(
            id = from.id,
            name = from.name,
            agency = agencyMapper.map(from.agencies.first()),
            mediaInfo = rocketMediaInfoMapper.map(from)
        )
}