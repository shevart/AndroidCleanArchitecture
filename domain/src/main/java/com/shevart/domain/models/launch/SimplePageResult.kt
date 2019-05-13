package com.shevart.domain.models.launch

data class SimplePageResult<T>(
    val launches: List<T>,
    val hasMoreItems: Boolean
)