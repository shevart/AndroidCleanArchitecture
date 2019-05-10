package com.shevart.data.models.mapper

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiLaunch
import com.shevart.data.models.ApiMission
import com.shevart.data.models.ApiRocket
import com.shevart.data.models.CountryMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.*
import javax.inject.Inject
import javax.inject.Named

class LaunchMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_ROCKET)
    private val rocketMapper: Mapper<ApiRocket, Rocket>,
    @Named(DataMapperName.DATA_MAPPER_MISSION)
    private val missionMapper: Mapper<ApiMission, Mission>,
    @Named(DataMapperName.DATA_MAPPER_LAUNCH_STATUS)
    private val statusMapper: Mapper<Int, LaunchStatus>,
    @Named(DataMapperName.DATA_MAPPER_COUNTRY)
    private val countryMapper: Mapper<String, Country>
) : Mapper<ApiLaunch, RocketLaunch>() {
    override fun map(from: ApiLaunch) =
        RocketLaunch(
            id = from.id,
            name = from.name,
            date = from.netDate,
            status = statusMapper.map(from.status),
            country = countryMapper.map(from.rocket.agencies.first().countryCode),
            rocket = rocketMapper.map(from.rocket),
            missions = missionMapper.mapList(from.missions),
            favorite = false
        )
}