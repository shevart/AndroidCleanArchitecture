package com.shevart.data.util

import com.shevart.data.models.ApiLaunch
import com.shevart.data.remote.impl.rest.response.GetLaunchesResponse
import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.RocketLaunch

fun GetLaunchesResponse.convertLaunchesResult(
    launchMapper: Mapper<ApiLaunch, RocketLaunch>
): PageResult<RocketLaunch> =
    PageResult(
        items = launchMapper.mapList(this.launches),
        count = this.count,
        totalCount = this.total,
        offset = this.offset
    )