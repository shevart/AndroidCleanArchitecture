package com.shevart.rocketlaunches.screen.home.launches

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmFragment
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.models.UILaunchStatus
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter
import kotlinx.android.synthetic.main.fragment_launches_list.*

class LaunchesListFragment : AbsMvvmFragment<LaunchesListViewModel>() {
    private lateinit var adapter: LaunchRVAdapter

    override fun provideLayoutResId() = R.layout.fragment_launches_list

    override fun provideViewModelClass() = LaunchesListViewModel::class.java

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LaunchRVAdapter()
        rvLaunches.layoutManager = LinearLayoutManager(requireContext())
        rvLaunches.adapter = adapter

        adapter.updateItems(
            listOf(
                UILaunch(
                    id = 1,
                    name = "Falcon - 1",
                    countryFlagResId = R.drawable.flag_usa,
                    countryName = "USA",
                    favoritesIconResId = R.drawable.ic_favorite_white,
                    date = "12.22.1999",
                    status = UILaunchStatus(
                        statusResId = R.string.app_name,
                        backgroundResId = R.drawable.gradient_splash_bg
                    )
                ),
                UILaunch(
                    id = 1,
                    name = "Falcon - 1",
                    countryFlagResId = R.drawable.flag_usa,
                    countryName = "USA",
                    favoritesIconResId = R.drawable.ic_favorite_white,
                    date = "12.22.1999",
                    status = UILaunchStatus(
                        statusResId = R.string.app_name,
                        backgroundResId = R.drawable.gradient_splash_bg
                    )
                ),
                UILaunch(
                    id = 1,
                    name = "Falcon - 1",
                    countryFlagResId = R.drawable.flag_usa,
                    countryName = "USA",
                    favoritesIconResId = R.drawable.ic_favorite_white,
                    date = "12.22.1999",
                    status = UILaunchStatus(
                        statusResId = R.string.app_name,
                        backgroundResId = R.drawable.gradient_splash_bg
                    )
                ),
                UILaunch(
                    id = 1,
                    name = "Falcon - 1",
                    countryFlagResId = R.drawable.flag_usa,
                    countryName = "USA",
                    favoritesIconResId = R.drawable.ic_favorite_white,
                    date = "12.22.1999",
                    status = UILaunchStatus(
                        statusResId = R.string.app_name,
                        backgroundResId = R.drawable.gradient_splash_bg
                    )
                )
            )
        )
    }
}
