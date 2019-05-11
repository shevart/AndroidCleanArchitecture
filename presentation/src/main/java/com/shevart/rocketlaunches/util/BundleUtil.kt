package com.shevart.rocketlaunches.util

import android.content.Intent

private const val WIKI_PAGE_URL = "wikiPageUrl"
private const val LAUNCH_ID = "launchId"

fun Intent.setWikiPageUrl(wikiPageUrl: String) = this.apply {
    putExtra(WIKI_PAGE_URL, wikiPageUrl)
}

fun Intent.getWikiPageUrl() =
    this.getStringExtra(WIKI_PAGE_URL)!!

fun Intent.setLaunchId(launchId: Long) = this.apply {
    putExtra(LAUNCH_ID, launchId)
}

fun Intent.getLaunchId(defaultValue: Long = -1) =
    this.getLongExtra(LAUNCH_ID, defaultValue)