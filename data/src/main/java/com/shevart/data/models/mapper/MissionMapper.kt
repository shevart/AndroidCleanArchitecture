package com.shevart.data.models.mapper

import com.shevart.data.models.ApiAgency
import com.shevart.data.models.ApiMission
import com.shevart.data.models.mapper.media.MissionMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Agency
import com.shevart.domain.models.launch.MediaInfo
import com.shevart.domain.models.launch.Mission

class MissionMapper(
    private val agencyMapper: Mapper<ApiAgency, Agency> = AgencyMapper(),
    private val mediaInfoMapper: Mapper<ApiMission, MediaInfo> = MissionMediaInfoMapper()
) : Mapper<ApiMission, Mission>() {
    override fun map(from: ApiMission) =
        Mission(
            id = from.id,
            name = from.name,
            description = from.description,
            type = from.type,
            typeName = from.typeName,
            agencies = agencyMapper.mapList(from.agencies),
            mediaInfo = mediaInfoMapper.map(from)
        )
}