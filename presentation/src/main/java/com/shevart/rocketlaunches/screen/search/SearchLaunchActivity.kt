package com.shevart.rocketlaunches.screen.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.adapter.ItemClickListener
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmActivity
import com.shevart.rocketlaunches.core.navigation.Launcher
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event.FinishSearching
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event.OpenLaunch
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State.*
import com.shevart.rocketlaunches.util.observeLiveDataForceNonNull
import com.shevart.rocketlaunches.util.ui.*
import kotlinx.android.synthetic.main.activity_search_launch.*
import kotlinx.android.synthetic.main.layout_empty_view.*

class SearchLaunchActivity : AbsMvvmActivity<SearchLaunchViewModel>() {
    private lateinit var adapter: SearchLaunchRVAdapter
    private val pagingListEndReachedListener: ListScrollItemListener by lazy {
        object : ListScrollItemListener(rvSearchItems.layoutManager!!) {
            override fun onEndListReached() {
                viewModel.onListEndReached()
            }
        }
    }

    override fun provideViewModelClass() = SearchLaunchViewModel::class.java

    override fun provideLayoutResId() = R.layout.activity_search_launch

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)

        etSearchLaunch.startEdit()
        tvSearchLaunchCancel.setOnClickListener { viewModel.cancel() }
        adapter = SearchLaunchRVAdapter()
        adapter.setItemClickListener(object : ItemClickListener<UILaunch> {
            override fun onItemClick(item: UILaunch, position: Int, view: View?) {
                viewModel.openLaunch(item)
            }
        })
        rvSearchItems.adapter = adapter
        rvSearchItems.layoutManager = LinearLayoutManager(this)
        rvSearchItems.addOnScrollListener(pagingListEndReachedListener)
        rvSearchItems.addItemDecoration(DividerItemDecoration(this, R.drawable.shape_list_divider))

        ivEmptyViewLogo.setImageResource(R.drawable.not_found)
        tvEmptyViewTitle.setText(R.string.error_not_found)
        tvEmptyViewDescription.setText(R.string.error_not_found_description)

        RxTextView.textChanges(etSearchLaunch)
            .skipInitialValue()
            .map { it.toString() }
            .subscribe(
                viewModel::findLaunches,
                this::defaultHandleException
            )
            .disposeOnDestroy()

        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)
        viewModel.getEventsObservable()
            .subscribe(
                this::handleEvent,
                this::defaultHandleException
            )
            .disposeOnDestroy()
    }

    private fun renderState(state: State) {
        when (state) {
            is EmptyList -> showEmptyList()
            is LaunchesNotFound -> showLaunchesNotFound()
            is ShowFoundLaunches -> showLaunches(state)
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is OpenLaunch -> Launcher.openWiki(this, event.launchId)
            is FinishSearching -> finish()
        }
    }

    private fun showEmptyList() {
        rvSearchItems.gone()
        vwSearchEmptyView.gone()
    }

    private fun showLaunchesNotFound() {
        vwSearchEmptyView.visible()
        rvSearchItems.gone()
    }

    private fun showLaunches(state: ShowFoundLaunches) {
        rvSearchItems.visible()
        vwSearchEmptyView.gone()
        // the order is important
        adapter.setShowLoadingBottomItem(state.showBottomProgress, refreshData = false)
        adapter.updateItems(state.launches)
    }
}
