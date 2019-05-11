package com.shevart.rocketlaunches.core.errorview

import com.shevart.rocketlaunches.screen.customview.error.ErrorView

interface ErrorViewHelper {
    fun showError(view: ErrorView, error: Throwable)
}