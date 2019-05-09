package com.shevart.data.util

import com.shevart.data.models.mapper.LaunchMapper
import com.shevart.data.remote.impl.rest.response.GetLaunchesResponse
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.models.launch.RocketLaunch

fun GetLaunchesResponse.convertLaunchesResult(launchMapper: LaunchMapper): PageResult<RocketLaunch> =
    PageResult(
        items = launchMapper.mapList(this.launches),
        count = this.count,
        totalCount = this.total,
        offset = this.offset
    )