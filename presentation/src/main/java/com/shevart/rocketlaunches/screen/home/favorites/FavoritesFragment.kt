package com.shevart.rocketlaunches.screen.home.favorites

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmFragment
import com.shevart.rocketlaunches.core.navigation.Launcher
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.Event.OpenLaunchDetail
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesViewModel.State.*
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter.LaunchItemClickListener
import com.shevart.rocketlaunches.util.animateChanges
import com.shevart.rocketlaunches.util.observeLiveDataForceNonNull
import com.shevart.rocketlaunches.util.ui.gone
import com.shevart.rocketlaunches.util.ui.visible
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.layout_empty_view.*
import kotlinx.android.synthetic.main.layout_favorites_content.*

class FavoritesFragment : AbsMvvmFragment<FavoritesViewModel>() {
    private lateinit var adapter: LaunchRVAdapter

    override fun provideViewModelClass() = FavoritesViewModel::class.java

    override fun provideLayoutResId() = R.layout.fragment_favorites

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivEmptyViewLogo.setImageResource(R.drawable.empty_favorites)
        tvEmptyViewTitle.setText(R.string.empty_favorites_title)
        tvEmptyViewDescription.setText(R.string.empty_favorites_description)

        adapter = LaunchRVAdapter()
        adapter.launchItemClickListener = object : LaunchItemClickListener {
            override fun onLaunchClick(launch: UILaunch) {
                viewModel.openLaunchDetail(launch)
            }

            override fun onLaunchFavoriteButtonClick(launch: UILaunch) {
                viewModel.removeFromFavorites(launch)
            }
        }
        rvFavorites.adapter = adapter
        rvFavorites.layoutManager = LinearLayoutManager(requireContext())

        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)
        viewModel.getEventsObservable()
            .subscribe(
                this::handleEvent,
                this::defaultHandleException
            )
            .disposeOnDestroyView()
    }

    private fun renderState(state: State) {
        flFavoritesRoot.animateChanges()
        when (state) {
            is Loading -> showLoading()
            is EmptyFavoritesList -> showEmptyView()
            is FavoritesList -> showFavoritesList(state)
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is OpenLaunchDetail -> Launcher.openWiki(requireActivity(), event.launchId)
        }
    }

    private fun showLoading() {
        vwLoadingView.visible()
        vwContent.visible()
        rvFavorites.gone()
        vwEmptyVIew.gone()
    }

    private fun showEmptyView() {
        vwEmptyVIew.visible()
        vwContent.gone()
        vwLoadingView.gone()
    }

    private fun showFavoritesList(state: FavoritesList) {
        vwContent.visible()
        rvFavorites.visible()
        vwEmptyVIew.gone()
        vwLoadingView.gone()

        adapter.updateItems(state.favorites)
    }
}
