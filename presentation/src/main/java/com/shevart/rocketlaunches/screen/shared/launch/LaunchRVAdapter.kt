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
import com.shevart.rocketlaunches.util.ui.loadInto

class LaunchRVAdapter : BaseRVAdapter<UILaunch, RecyclerView.ViewHolder>() {
    var launchItemClickListener: LaunchItemClickListener? = null
    // I prefer use special UI models for loaders like this, but I have no time for do it.
    private var showLoadingBottomItem = false

    override fun getItemCount(): Int {
        return if (showLoadingBottomItem && super.getItemCount() > 0) {
            super.getItemCount() + 1
        } else {
            super.getItemCount()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (showLoadingBottomItem && position == (itemCount - 1)) {
            LOADING_ITEM_VIEW_TYPE
        } else {
            RECORD_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            RECORD_VIEW_TYPE -> {
                LaunchViewHolder(inflate(parent, R.layout.item_rocket_launch)).apply {
                    clLaunchItemContent.setOnClickListener {
                        launchItemClickListener?.onLaunchClick(launch = getItem(adapterPosition))
                    }
                    ivRocketLaunchItemFavorite.setOnClickListener {
                        launchItemClickListener
                            ?.onLaunchFavoriteButtonClick(launch = getItem(adapterPosition))
                    }
                }
            }
            LOADING_ITEM_VIEW_TYPE -> {
                BottomLoadingViewHolder(inflate(parent, R.layout.item_progress_bottom))
            }
            else -> throwCreateViewHolderIllegalViewType(viewType)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LaunchViewHolder) {
            bindLaunchItem(holder, position)
        } else {
            // do nothing
        }
    }

    fun setShowLoadingBottomItem(showLoadingBottomItem: Boolean, refreshData: Boolean = true) {
        this.showLoadingBottomItem = showLoadingBottomItem
        if (refreshData) {
            notifyDataSetChanged()
        }
    }

    private fun bindLaunchItem(holder: LaunchViewHolder, position: Int) {
        val item = getItem(position)
        holder.ivRocketLaunchItemFlag.setImageResource(item.countryFlagResId)
        holder.tvRocketLaunchItemCountry.setText(item.countryNameResId)
        holder.ivRocketLaunchItemFavorite.setImageResource(item.favoritesIconResId)
        holder.tvRocketLaunchItemTitle.text = item.name
        holder.tvRocketLaunchItemDate.text = item.date
        holder.svRocketLaunchItemStatus.setText(item.status.statusResId)
        holder.svRocketLaunchItemStatus.setStatusBg(item.status.backgroundResId)
        if (item.imageUrl != null) {
            holder.ivRocketLaunchItemCover.loadInto(item.imageUrl)
        } else {
            holder.ivRocketLaunchItemCover.setImageResource(R.drawable.rocket_list_default)
        }
    }

    @Suppress("JoinDeclarationAndAssignment")
    class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clLaunchItemContent: View
        val ivRocketLaunchItemFlag: ImageView
        val ivRocketLaunchItemFavorite: ImageView
        val ivRocketLaunchItemCover: ImageView
        val tvRocketLaunchItemCountry: TextView
        val tvRocketLaunchItemTitle: TextView
        val tvRocketLaunchItemDate: TextView
        val svRocketLaunchItemStatus: LaunchStatusView

        init {
            clLaunchItemContent = itemView.findViewById(R.id.clLaunchItemContent)
            ivRocketLaunchItemFlag = itemView.findViewById(R.id.ivRocketLaunchItemFlag)
            ivRocketLaunchItemFavorite = itemView.findViewById(R.id.ivRocketLaunchItemFavorite)
            ivRocketLaunchItemCover = itemView.findViewById(R.id.ivRocketLaunchItemCover)
            tvRocketLaunchItemCountry = itemView.findViewById(R.id.tvRocketLaunchItemCountry)
            tvRocketLaunchItemTitle = itemView.findViewById(R.id.tvRocketLaunchItemTitle)
            tvRocketLaunchItemDate = itemView.findViewById(R.id.tvRocketLaunchItemDate)
            svRocketLaunchItemStatus = itemView.findViewById(R.id.svRocketLaunchItemStatus)
        }
    }

    class BottomLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private companion object {
        private const val RECORD_VIEW_TYPE = 1
        private const val LOADING_ITEM_VIEW_TYPE = 2
    }

    interface LaunchItemClickListener {
        fun onLaunchClick(launch: UILaunch)

        fun onLaunchFavoriteButtonClick(launch: UILaunch)
    }
}