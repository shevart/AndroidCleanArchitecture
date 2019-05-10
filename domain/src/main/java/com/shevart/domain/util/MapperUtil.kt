package com.shevart.domain.util

import com.shevart.domain.contract.mapper.Mapper

fun <From, To> Mapper<From, To>.mapListOrEmpty(list: List<From>?): List<To> {
    return if (list != null) {
        this.mapList(list)
    } else {
        emptyList()
    }
}

fun <From, To> Mapper<From, To>.mapOrNull(source: From?): To? {
    return if (source != null) {
        this.map(source)
    } else {
        null
    }
}