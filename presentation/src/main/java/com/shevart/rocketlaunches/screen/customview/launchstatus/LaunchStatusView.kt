package com.shevart.rocketlaunches.screen.customview.launchstatus

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.shevart.rocketlaunches.R
import kotlinx.android.synthetic.main.layout_custom_view_launch_status.view.*

class LaunchStatusView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.layout_custom_view_launch_status, this)
    }

    fun setText(@StringRes textResId: Int) {
        tvRocketLaunchStatus.setText(textResId)
    }

    fun setStatusBg(@DrawableRes backgroundResId: Int) {
        tvRocketLaunchStatus.setBackgroundResource(backgroundResId)
    }
}