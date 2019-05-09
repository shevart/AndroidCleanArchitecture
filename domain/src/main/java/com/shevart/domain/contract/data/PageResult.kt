package com.shevart.domain.contract.data

class PageResult<T>(
    val items: List<T>,
    val count: Int,
    val totalCount: Int,
    val offset: Int
)