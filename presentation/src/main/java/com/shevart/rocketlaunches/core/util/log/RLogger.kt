package com.shevart.rocketlaunches.core.util.log

import android.util.Log
import com.shevart.domain.contract.util.RLog
import com.shevart.rocketlaunches.BuildConfig

object RLogger : RLog {
    override fun log(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg)
        }
    }

    override fun log(e: Throwable) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
    }
}