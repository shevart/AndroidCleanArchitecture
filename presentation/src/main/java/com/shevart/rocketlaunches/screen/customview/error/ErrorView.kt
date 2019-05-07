package com.shevart.rocketlaunches.screen.customview.error

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.shevart.rocketlaunches.R
import kotlinx.android.synthetic.main.layout_custom_view_error_view.view.*

class ErrorView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.layout_custom_view_error_view, this)
    }

    fun setBackgroundColorResId(@ColorRes colorResId: Int) {
        llErrorViewRoot.setBackgroundResource(colorResId)
    }

    fun setTitle(@StringRes stringResId: Int) {
        tvErrorViewTitle.setText(stringResId)
    }

    fun setDescription(@StringRes stringResId: Int) {
        tvErrorViewDescription.setText(stringResId)
    }

    fun setImage(@DrawableRes imageResId: Int) {
        ivErrorViewImage.setImageResource(imageResId)
    }
}