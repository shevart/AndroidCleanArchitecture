package com.shevart.data.models.mapper.media

import com.shevart.data.models.ApiMission
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.MediaInfo
import javax.inject.Inject

class MissionMediaInfoMapper
@Inject constructor() : Mapper<ApiMission, MediaInfo>() {
    override fun map(from: ApiMission) =
        MediaInfo(
            wikiLink = from.wikiUrl ?: ""
        )
}