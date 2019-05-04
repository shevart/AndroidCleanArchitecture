package com.shevart.domain.contract.mapper

abstract class Mapper<From, To> {
    abstract fun map(from: From): To

    open fun reverseMap(to: To): From {
        throw NotImplementedError("Not implemented method")
    }

    open fun mapList(typeList: List<From>): List<To> =
        typeList.map(this::map)

    open fun reverseMapList(typeList: List<To>): List<From> =
        typeList.map(this::reverseMap)
}