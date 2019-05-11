package com.shevart.rocketlaunches.core.navigation

import android.app.Activity
import android.content.Intent
import com.shevart.rocketlaunches.screen.detail.WikiPageActivity
import com.shevart.rocketlaunches.util.checkIsWikiUrlValid
import com.shevart.rocketlaunches.util.checkLaunchId
import com.shevart.rocketlaunches.util.setLaunchId
import com.shevart.rocketlaunches.util.setWikiPageUrl

object Launcher {
    fun openWiki(activity: Activity, launchId: Long) {
        launchId.checkLaunchId()
        val intent = Intent(activity, WikiPageActivity::class.java)
            .setLaunchId(launchId)
        activity.startActivity(intent)
    }
}