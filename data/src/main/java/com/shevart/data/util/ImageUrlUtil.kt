package com.shevart.data.util

private const val DOT = '.'
private const val FORMAT_INSERT = "%d"

fun String.prepareImageUrlForFormat(): String {
    val endIndex = this.indexOfLast { it == DOT }
    val startIndex = this.findStartIndexOfResolution(endIndex)

    return this.substring(0, startIndex) + FORMAT_INSERT + this.substring(endIndex)
}

private fun String.findStartIndexOfResolution(endIndex: Int): Int {
    var i = endIndex
    while (this[--i].isDigit()) {
        // do nothing
    }
    return i + 1
}

