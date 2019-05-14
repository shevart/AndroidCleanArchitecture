package com.shevart.rocketlaunches.screen.search

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.R.drawable
import com.shevart.rocketlaunches.base.adapter.BaseRVAdapter
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.util.ui.loadInto

class SearchLaunchRVAdapter : BaseRVAdapter<UILaunch, RecyclerView.ViewHolder>() {
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
            LAUNCH_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LAUNCH_VIEW_TYPE -> {
                LaunchViewHolder(inflate(parent, R.layout.item_found_launch)).apply {
                    rootView.setOnClickListener {
                        getItemClickListener()
                            ?.onItemClick(getItem(adapterPosition), adapterPosition, rootView)
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
            bindLaunchItem(holder, getItem(position))
        }
    }

    fun setShowLoadingBottomItem(showLoadingBottomItem: Boolean, refreshData: Boolean = true) {
        this.showLoadingBottomItem = showLoadingBottomItem
        if (refreshData) {
            notifyDataSetChanged()
        }
    }

    private fun bindLaunchItem(holder: LaunchViewHolder, launch: UILaunch) {
        if (!launch.imageUrl.isNullOrBlank()) {
            holder.ivSearchLaunch.loadInto(launch.imageUrl)
        } else {
            holder.ivSearchLaunch.setImageResource(drawable.rocket_list_default)
        }
        holder.ivSearchLaunchFlag.setImageResource(launch.countryFlagResId)
        holder.tvSearchLaunchTitle.text = launch.name
        holder.tvSearchLaunchStatus.setText(launch.status.statusResId)
    }

    @Suppress("JoinDeclarationAndAssignment")
    class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rootView: View
        val ivSearchLaunch: ImageView
        val ivSearchLaunchFlag: ImageView
        val tvSearchLaunchTitle: TextView
        val tvSearchLaunchStatus: TextView

        init {
            rootView = itemView.findViewById(R.id.clSearchLaunchRoot)
            ivSearchLaunch = itemView.findViewById(R.id.ivSearchLaunch)
            ivSearchLaunchFlag = itemView.findViewById(R.id.ivSearchLaunchFlag)
            tvSearchLaunchTitle = itemView.findViewById(R.id.tvSearchLaunchTitle)
            tvSearchLaunchStatus = itemView.findViewById(R.id.tvSearchLaunchStatus)
        }
    }

    class BottomLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private companion object {
        private const val LAUNCH_VIEW_TYPE = 1
        private const val LOADING_ITEM_VIEW_TYPE = 2
    }
}