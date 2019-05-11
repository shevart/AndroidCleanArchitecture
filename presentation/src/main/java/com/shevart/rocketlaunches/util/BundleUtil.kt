package com.shevart.rocketlaunches.util

import android.content.Intent

private const val WIKI_PAGE_URL = "wikiPageUrl"

fun Intent.setWikiPageUrl(wikiPageUrl: String) = this.apply {
    putExtra(WIKI_PAGE_URL, wikiPageUrl)
}

fun Intent.getWikiPageUrl() = this.getStringExtra(WIKI_PAGE_URL)!!