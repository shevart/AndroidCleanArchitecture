package com.shevart.rocketlaunches.util

import android.util.Patterns

fun String?.checkIsWikiUrlValid() {
    if (isNullOrBlank()) {
        throw IllegalArgumentException("Invalid wiki page url ($this)!")
    }
    this?.checkIsValidWebUrl()
}

fun String.checkIsValidWebUrl() {
    if (!Patterns.WEB_URL.matcher(this).matches()) {
        throw IllegalArgumentException("Invalid web url ($this)!")
    }
}

fun Long.checkLaunchId() {
    if (this == 0L) {
        throw IllegalArgumentException("There is a very bad launchId - $this!")
    }
}