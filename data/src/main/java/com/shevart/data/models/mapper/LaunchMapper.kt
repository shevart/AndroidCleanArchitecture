package com.shevart.data.models.mapper

import com.shevart.data.models.ApiLaunch
import com.shevart.data.models.ApiMission
import com.shevart.data.models.ApiRocket
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Mission
import com.shevart.domain.models.launch.Rocket
import com.shevart.domain.models.launch.RocketLaunch

class LaunchMapper(
    private val rocketMapper: Mapper<ApiRocket, Rocket> = RocketMapper(),
    private val missionMapper: Mapper<ApiMission, Mission> = MissionMapper()
) : Mapper<ApiLaunch, RocketLaunch>() {
    override fun map(from: ApiLaunch) =
        RocketLaunch(
            id = from.id,
            name = from.name,
            date = from.netDate,
            rocket = rocketMapper.map(from.rocket),
            missions = missionMapper.mapList(from.missions),
            favorite = false
        )
}