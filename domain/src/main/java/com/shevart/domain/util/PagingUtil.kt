package com.shevart.domain.util

import com.shevart.domain.contract.data.PageResult
import com.shevart.domain.models.launch.SimplePageResult
import io.reactivex.Single

fun <T> Single<PageResult<T>>.convertPageResultToSimplePageResult() =
    this.map { result ->
        SimplePageResult(
            launches = result.items,
            hasMoreItems = (result.offset + result.count) < result.totalCount
        )
    }