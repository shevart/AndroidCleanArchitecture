package com.shevart.rocketlaunches.screen.home.launches

import android.os.Bundle
import android.view.View
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager

import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmFragment
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.models.UILaunchStatus
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.Error
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.Loading
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.ShowLaunchesList
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter
import com.shevart.rocketlaunches.util.observeLiveDataForceNonNull
import com.shevart.rocketlaunches.util.ui.gone
import com.shevart.rocketlaunches.util.ui.textColorByColorId
import com.shevart.rocketlaunches.util.ui.visible
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


        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)

        // todo remove after test
        rvLaunches.postDelayed(2000L) {
            TransitionManager.beginDelayedTransition(flLaunchesRoot)
            evLaunchesError.gone()
            tvLaunchesTitle.textColorByColorId(R.color.greyDark)
            ivLaunchesLoading.visible()
            ivLaunchesLoading.postDelayed(1000L) {
                showContent()
            }
        }

        tvLaunchesTitle.textColorByColorId(R.color.white)
        evLaunchesError.setBackgroundColorResId(R.color.errorNoInternet)
        evLaunchesError.setImage(R.drawable.error_no_internet)
        evLaunchesError.setTitle(R.string.error_no_internet)
        evLaunchesError.setDescription(R.string.error_no_internet)
    }

    private fun renderState(state: State) {
        when (state) {
            is Loading -> showLoading()
            is ShowLaunchesList -> showLaunches(state)
            is Error -> showError(state)
        }
    }

    private fun showLoading() {
        ivLaunchesLoading.visible()
        rvLaunches.gone()
        evLaunchesError.gone()
    }

    private fun showLaunches(state: ShowLaunchesList) {
        ivLaunchesLoading.gone()
        evLaunchesError.gone()
        rvLaunches.visible()
        adapter.updateItems(state.launchesItems)
    }

    private fun showError(state: Error) {
        ivLaunchesLoading.gone()
        rvLaunches.gone()
        evLaunchesError.visible()
        // todo
        tvLaunchesTitle.textColorByColorId(R.color.white)
        evLaunchesError.setBackgroundColorResId(R.color.errorNoInternet)
        evLaunchesError.setImage(R.drawable.error_no_internet)
        evLaunchesError.setTitle(R.string.error_no_internet)
        evLaunchesError.setDescription(R.string.error_no_internet)
    }

    private fun showContent() {
        ivLaunchesLoading.gone()
        adapter.updateItems(getItems())
    }

    private fun getItems() = listOf(
        UILaunch(
            id = 1,
            name = "Falcon - 1",
            countryFlagResId = R.drawable.flag_usa,
            countryName = "USA",
            favoritesIconResId = R.drawable.ic_favorite_white,
            date = "12.22.1999",
            status = UILaunchStatus(
                statusResId = R.string.launch_status_successfully,
                backgroundResId = R.drawable.gradient_status_green
            ),
            imageUrl = "https://s3.amazonaws.com/launchlibrary/RocketImages/Falcon1.jpg_720.jpg"
        ),
        UILaunch(
            id = 1,
            name = "Falcon - 1 by Elon Musk by Tesla and SpaceX",
            countryFlagResId = R.drawable.flag_usa,
            countryName = "USA",
            favoritesIconResId = R.drawable.ic_favorite_red,
            date = "12.22.1999",
            status = UILaunchStatus(
                statusResId = R.string.launch_status_failed,
                backgroundResId = R.drawable.gradient_status_red
            ),
            imageUrl = "https://s3.amazonaws.com/launchlibrary/RocketImages/Falcon+9+v1.0_720.jpg"
        ),
        UILaunch(
            id = 1,
            name = "Falcon - 1",
            countryFlagResId = R.drawable.flag_usa,
            countryName = "USA",
            favoritesIconResId = R.drawable.ic_favorite_white,
            date = "12.22.1999",
            status = UILaunchStatus(
                statusResId = R.string.launch_status_scheduled,
                backgroundResId = R.drawable.gradient_status_blue
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
                statusResId = R.string.launch_status_canceled,
                backgroundResId = R.drawable.gradient_status_grey
            )
        )
    )
}
