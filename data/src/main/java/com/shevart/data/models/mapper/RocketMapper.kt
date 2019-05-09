package com.shevart.data.models.mapper

import com.shevart.data.models.ApiAgency
import com.shevart.data.models.ApiRocket
import com.shevart.data.models.mapper.media.RocketMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Agency
import com.shevart.domain.models.launch.MediaInfo
import com.shevart.domain.models.launch.Rocket

class RocketMapper(
    private val agencyMapper: Mapper<ApiAgency, Agency> = AgencyMapper(),
    private val rocketMediaInfoMapper: Mapper<ApiRocket, MediaInfo> = RocketMediaInfoMapper()
) : Mapper<ApiRocket, Rocket>() {
    override fun map(from: ApiRocket) =
        Rocket(
            id = from.id,
            name = from.name,
            agency = agencyMapper.map(from.agencies.first()),
            mediaInfo = rocketMediaInfoMapper.map(from)
        )
}