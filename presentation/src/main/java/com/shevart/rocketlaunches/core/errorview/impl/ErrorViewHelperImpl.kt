package com.shevart.rocketlaunches.core.errorview.impl

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.core.errorview.ErrorViewHelper
import com.shevart.rocketlaunches.screen.customview.error.ErrorView
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorViewHelperImpl
@Inject constructor() : ErrorViewHelper {
    override fun showError(view: ErrorView, error: Throwable) {
        view.apply {
            setBackgroundColorResId(error.getBackgroundColorBg())
            setImage(error.getImage())
            setTitle(error.getTitle())
            val description = error.getDescription()
            if (description != 0) {
                setDescription(description)
            } else {
                setDescription(error.getStringRepresentation())
            }
        }
    }

    @Suppress("unused")
    private companion object {
        @ColorRes
        fun Throwable.getBackgroundColorBg() =
            when (this) {
                is UnknownHostException -> R.color.error_data_loading
                else -> R.color.error_unknown
            }

        @DrawableRes
        fun Throwable.getImage() =
            when (this) {
                is UnknownHostException -> R.drawable.error_data_loading
                else -> R.drawable.error_unknown
            }

        @StringRes
        fun Throwable.getTitle() =
            when (this) {
                is UnknownHostException -> R.string.error_no_internet
                else -> R.string.error_unknown
            }

        @StringRes
        fun Throwable.getDescription() =
            when (this) {
                is UnknownHostException -> R.string.error_no_internet_description
                else -> 0
            }

        fun Throwable.getStringRepresentation(): String =
            this.localizedMessage
                ?: this.message
                ?: this.toString()
    }
}