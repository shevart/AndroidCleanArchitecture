package com.shevart.data.util

import com.shevart.data.models.mapper.LaunchMapper
import com.shevart.domain.contract.data.PageResult

val launchMapper = LaunchMapper()

val pageResultWithLaunchesList = PageResult(
    items = launchMapper.mapList(createApiLaunchesList(count = 3)),
    count = 3,
    offset = 0,
    totalCount = 6
)