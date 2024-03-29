package com.shevart.data.models.mapper

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiAgency
import com.shevart.data.models.ApiMission
import com.shevart.data.models.mapper.media.MissionMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Agency
import com.shevart.domain.models.launch.MediaInfo
import com.shevart.domain.models.launch.Mission
import com.shevart.domain.util.mapListOrEmpty
import javax.inject.Inject
import javax.inject.Named

class MissionMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_AGENCY)
    private val agencyMapper: Mapper<ApiAgency, Agency>,
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_MISSION)
    private val mediaInfoMapper: Mapper<ApiMission, MediaInfo>
) : Mapper<ApiMission, Mission>() {
    override fun map(from: ApiMission) =
        Mission(
            id = from.id,
            name = from.name,
            description = from.description,
            type = from.type,
            typeName = from.typeName,
            agencies = agencyMapper.mapListOrEmpty(from.agencies),
            mediaInfo = mediaInfoMapper.map(from)
        )
}