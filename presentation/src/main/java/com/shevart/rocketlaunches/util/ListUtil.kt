package com.shevart.rocketlaunches.util

fun <M> MutableList<M>.replaceAll(items: List<M>) {
    this.clear()
    this.addAll(items)
}
