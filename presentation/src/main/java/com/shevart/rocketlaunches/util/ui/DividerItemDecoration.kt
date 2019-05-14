package com.shevart.rocketlaunches.util.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

// code from https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
class DividerItemDecoration(context: Context, @DrawableRes dividerResId: Int) :
    RecyclerView.ItemDecoration() {
    private var divider: Drawable? = null

    init {
        divider = ContextCompat.getDrawable(context, dividerResId)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight

            divider!!.setBounds(left, top, right, bottom)
            divider!!.draw(c)
        }
    }
}