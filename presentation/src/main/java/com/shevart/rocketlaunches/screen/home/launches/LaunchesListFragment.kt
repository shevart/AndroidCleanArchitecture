package com.shevart.rocketlaunches.screen.home.launches

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmFragment
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.*
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter
import com.shevart.rocketlaunches.util.observeLiveDataForceNonNull
import com.shevart.rocketlaunches.util.ui.ListScrollItemListener
import com.shevart.rocketlaunches.util.ui.gone
import com.shevart.rocketlaunches.util.ui.textColorByColorId
import com.shevart.rocketlaunches.util.ui.visible
import kotlinx.android.synthetic.main.fragment_launches_list.*

class LaunchesListFragment : AbsMvvmFragment<LaunchesListViewModel>() {
    private lateinit var adapter: LaunchRVAdapter
    private val pagingListEndReachedListener: ListScrollItemListener by lazy {
        object : ListScrollItemListener(rvLaunches.layoutManager!!) {
            override fun onEndListReached() {
                viewModel.onListEndReached()
            }
        }
    }

    override fun provideLayoutResId() = R.layout.fragment_launches_list

    override fun provideViewModelClass() = LaunchesListViewModel::class.java

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = LaunchRVAdapter()
        rvLaunches.layoutManager = LinearLayoutManager(requireContext())
        rvLaunches.adapter = adapter
        rvLaunches.addOnScrollListener(pagingListEndReachedListener)

        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)
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
        tvLaunchesTitle.textColorByColorId(R.color.greyDark)
        adapter.setShowLoadingBottomItem(state.showBottomListLoadingIndicator, refreshData = false)
        adapter.updateItems(state.launchesItems)
    }

    private fun showError(state: Error) {
        ivLaunchesLoading.gone()
        rvLaunches.gone()
        evLaunchesError.visible()
        tvLaunchesTitle.textColorByColorId(R.color.white)
        errorViewHelper.showError(evLaunchesError, state.error)
    }
}
