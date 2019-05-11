package com.shevart.rocketlaunches.core.navigation

import android.app.Activity
import android.content.Intent
import com.shevart.rocketlaunches.screen.detail.WikiPageActivity
import com.shevart.rocketlaunches.util.checkIsWikiUrlValid
import com.shevart.rocketlaunches.util.setWikiPageUrl

object Launcher {
    fun openWiki(activity: Activity, wikiPage: String) {
        wikiPage.checkIsWikiUrlValid()
        val intent = Intent(activity, WikiPageActivity::class.java)
            .setWikiPageUrl(wikiPage)
        activity.startActivity(intent)
    }
}