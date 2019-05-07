package com.shevart.rocketlaunches.util.ui

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.textColorByColorId(@ColorRes colorId: Int) {
    this.setTextColor(ContextCompat.getColor(context, colorId))
}
