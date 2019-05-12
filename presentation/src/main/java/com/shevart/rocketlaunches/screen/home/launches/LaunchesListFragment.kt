package com.shevart.rocketlaunches.screen.home.launches

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.adapter.ItemClickListener
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmFragment
import com.shevart.rocketlaunches.core.navigation.Launcher
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.Event.OpenLaunchDetail
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListViewModel.State.*
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter.LaunchItemClickListener
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
        adapter.launchItemClickListener = object : LaunchItemClickListener {
            override fun onLaunchClick(launch: UILaunch) {
                viewModel.openLaunchDetail(launch)
            }

            override fun onLaunchFavoriteButtonClick(launch: UILaunch) {
                viewModel.favoriteButtonClick(launch)
            }
        }
        rvLaunches.layoutManager = LinearLayoutManager(requireContext())
        rvLaunches.adapter = adapter
        rvLaunches.addOnScrollListener(pagingListEndReachedListener)

        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)
        viewModel.getEventsObservable()
            .subscribe(
                this::handleEvent,
                this::defaultHandleException
            )
            .disposeOnDestroyView()
    }

    private fun renderState(state: State) {
        TransitionManager.beginDelayedTransition(flLaunchesRoot)
        when (state) {
            is Loading -> showLoading()
            is ShowLaunchesList -> showLaunches(state)
            is Error -> showError(state)
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is OpenLaunchDetail -> Launcher.openWiki(requireActivity(), event.launchId)
        }
    }

    private fun showLoading() {
        rvLaunches.gone()
        evLaunchesError.gone()
        ivLaunchesLoading.visible()
    }

    private fun showLaunches(state: ShowLaunchesList) {
        rvLaunches.visible()
        evLaunchesError.gone()
        ivLaunchesLoading.gone()
        tvLaunchesTitle.textColorByColorId(R.color.greyDark)
        // the order is important
        adapter.setShowLoadingBottomItem(state.showBottomListLoadingIndicator, refreshData = false)
        adapter.updateItems(state.launchesItems)
    }

    private fun showError(state: Error) {
        rvLaunches.gone()
        ivLaunchesLoading.gone()
        evLaunchesError.visible()
        tvLaunchesTitle.textColorByColorId(R.color.white)
        errorViewHelper.showError(evLaunchesError, state.error)
    }
}
