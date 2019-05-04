package com.shevart.rocketlaunches.base.adapter

import android.view.View

@FunctionalInterface
interface ItemClickListener<M> {
    fun onItemClick(item: M, position: Int, view: View)
}