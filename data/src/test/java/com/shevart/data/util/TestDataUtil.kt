package com.shevart.data.util

import com.shevart.data.models.CountryMapper
import com.shevart.data.models.mapper.*
import com.shevart.data.models.mapper.media.AgencyMediaInfoMapper
import com.shevart.data.models.mapper.media.ImageMapper
import com.shevart.data.models.mapper.media.MissionMediaInfoMapper
import com.shevart.data.models.mapper.media.RocketMediaInfoMapper
import com.shevart.domain.contract.data.PageResult

val imageMapper = ImageMapper()

val agencyMapper = AgencyMapper(
    agencyMediaInfoMapper = AgencyMediaInfoMapper()
)
val rocketMapper = RocketMapper(
    agencyMapper = agencyMapper,
    rocketMediaInfoMapper = RocketMediaInfoMapper(
        imageMapper = imageMapper
    )
)

val missionMapper = MissionMapper(
    agencyMapper = agencyMapper,
    mediaInfoMapper = MissionMediaInfoMapper()
)

val launchMapper = LaunchMapper(
    rocketMapper = rocketMapper,
    missionMapper = missionMapper,
    statusMapper = LaunchStatusMapper(),
    countryMapper = CountryMapper()
)

val pageResultWithLaunchesList = PageResult(
    items = launchMapper.mapList(createApiLaunchesList(count = 3)),
    count = 3,
    offset = 0,
    totalCount = 6
)