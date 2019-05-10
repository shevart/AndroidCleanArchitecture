package com.shevart.rocketlaunches.util

fun <M> MutableList<M>.replaceAll(items: List<M>) {
    this.clear()
    this.addAll(items)
}

fun <M> List<M>.plus(items: List<M>): List<M> {
    val result = ArrayList(this)
    result.addAll(items)
    return result
}