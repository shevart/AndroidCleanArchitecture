package com.shevart.rocketlaunches.util.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateView(@LayoutRes res: Int): View {
    return LayoutInflater.from(context).inflate(res, this, false)
}