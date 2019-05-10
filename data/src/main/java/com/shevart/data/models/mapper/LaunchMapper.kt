package com.shevart.data.models.mapper

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiLaunch
import com.shevart.data.models.ApiMission
import com.shevart.data.models.ApiRocket
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Mission
import com.shevart.domain.models.launch.Rocket
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.domain.models.launch.LaunchStatus
import javax.inject.Inject
import javax.inject.Named

class LaunchMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_ROCKET)
    private val rocketMapper: Mapper<ApiRocket, Rocket>,
    @Named(DataMapperName.DATA_MAPPER_MISSION)
    private val missionMapper: Mapper<ApiMission, Mission>,
    @Named(DataMapperName.DATA_MAPPER_LAUNCH_STATUS)
    private val statusMapper: Mapper<Int, LaunchStatus>
) : Mapper<ApiLaunch, RocketLaunch>() {
    override fun map(from: ApiLaunch) =
        RocketLaunch(
            id = from.id,
            name = from.name,
            date = from.netDate,
            status = statusMapper.map(from.status),
            rocket = rocketMapper.map(from.rocket),
            missions = missionMapper.mapList(from.missions),
            favorite = false
        )
}