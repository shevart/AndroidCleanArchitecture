package com.shevart.rocketlaunches.util

import android.view.ViewGroup
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.shevart.domain.contract.app.AnimationConfig

val defaultTransition = AutoTransition().apply {
    this.duration = AnimationConfig.DEFAULT_ANIMATION_DURATION
}

fun ViewGroup.animateChanges() {
    TransitionManager.beginDelayedTransition(this, defaultTransition)
}