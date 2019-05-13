package com.shevart.rocketlaunches.screen.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmActivity
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.Event.FinishSearching
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State
import com.shevart.rocketlaunches.screen.search.SearchLaunchViewModel.State.*
import com.shevart.rocketlaunches.screen.shared.launch.LaunchRVAdapter
import com.shevart.rocketlaunches.util.observeLiveDataForceNonNull
import com.shevart.rocketlaunches.util.ui.gone
import com.shevart.rocketlaunches.util.ui.startEdit
import com.shevart.rocketlaunches.util.ui.visible
import kotlinx.android.synthetic.main.activity_search_launch.*

class SearchLaunchActivity : AbsMvvmActivity<SearchLaunchViewModel>() {
    private lateinit var adapter: LaunchRVAdapter

    override fun provideViewModelClass() = SearchLaunchViewModel::class.java

    override fun provideLayoutResId() = R.layout.activity_search_launch

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)

        etSearchLaunch.startEdit()
        tvSearchLaunchCancel.setOnClickListener { viewModel.cancel() }
        adapter = LaunchRVAdapter()
        rvSearchItems.adapter = adapter
        rvSearchItems.layoutManager = LinearLayoutManager(this)

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
            is FinishSearching -> finish()
        }
    }

    private fun showEmptyList() {
        rvSearchItems.gone()
    }

    private fun showLaunchesNotFound() {

    }

    private fun showLaunches(state: ShowFoundLaunches) {
        rvSearchItems.visible()
        adapter.updateItems(state.launches)
    }
}
