package com.shevart.rocketlaunches.screen.shared.launch

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.adapter.BaseRVAdapter
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.customview.launchstatus.LaunchStatusView
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter.LaunchViewHolder
import com.shevart.rocketlaunches.util.ui.loadInto

class LaunchRVAdapter : BaseRVAdapter<UILaunch, LaunchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(inflate(parent, R.layout.item_rocket_launch))
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val item = getItem(position)
        holder.ivRocketLaunchItemFlag.setImageResource(item.countryFlagResId)
        holder.tvRocketLaunchItemCountry.text = item.countryName
        holder.ivRocketLaunchItemFavorite.setImageResource(item.favoritesIconResId)
        holder.tvRocketLaunchItemTitle.text = item.name
        holder.tvRocketLaunchItemDate.text = item.date
        holder.svRocketLaunchItemStatus.setText(item.status.statusResId)
        holder.svRocketLaunchItemStatus.setStatusBg(item.status.backgroundResId)
        if (item.imageUrl != null) {
            holder.ivRocketLaunchItemCover.loadInto(item.imageUrl)
        }
    }

    @Suppress("JoinDeclarationAndAssignment")
    class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivRocketLaunchItemFlag: ImageView
        val ivRocketLaunchItemFavorite: ImageView
        val ivRocketLaunchItemCover: ImageView
        val tvRocketLaunchItemCountry: TextView
        val tvRocketLaunchItemTitle: TextView
        val tvRocketLaunchItemDate: TextView
        val svRocketLaunchItemStatus: LaunchStatusView

        init {
            ivRocketLaunchItemFlag = itemView.findViewById(R.id.ivRocketLaunchItemFlag)
            ivRocketLaunchItemFavorite = itemView.findViewById(R.id.ivRocketLaunchItemFavorite)
            ivRocketLaunchItemCover = itemView.findViewById(R.id.ivRocketLaunchItemCover)
            tvRocketLaunchItemCountry = itemView.findViewById(R.id.tvRocketLaunchItemCountry)
            tvRocketLaunchItemTitle = itemView.findViewById(R.id.tvRocketLaunchItemTitle)
            tvRocketLaunchItemDate = itemView.findViewById(R.id.tvRocketLaunchItemDate)
            svRocketLaunchItemStatus = itemView.findViewById(R.id.svRocketLaunchItemStatus)
        }
    }
}