package com.shevart.rocketlaunches.screen.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmActivity
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.Event
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.Event.ShowErrorAlert
import com.shevart.rocketlaunches.screen.detail.WikiPageViewModel.State
import com.shevart.rocketlaunches.util.getLaunchId
import com.shevart.rocketlaunches.util.getWikiPageUrl
import com.shevart.rocketlaunches.util.observeLiveDataForceNonNull
import com.shevart.rocketlaunches.util.setForWiki
import com.shevart.rocketlaunches.util.ui.gone
import com.shevart.rocketlaunches.util.ui.visible
import kotlinx.android.synthetic.main.activity_favorite_wiki_page.*
import kotlinx.android.synthetic.main.layout_empty_view.*

class WikiPageActivity : AbsMvvmActivity<WikiPageViewModel>() {
    // quick fix for favorite icon.
    @DrawableRes
    private var favoriteIconResId: Int = 0
    private var favoriteMenuItem: MenuItem? = null

    override fun provideViewModelClass() = WikiPageViewModel::class.java

    override fun provideLayoutResId() = R.layout.activity_favorite_wiki_page

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.wiki)
        wbWiki.setForWiki()
        ivEmptyViewLogo.setImageResource(R.drawable.empty_wiki_page)
        tvEmptyViewTitle.setText(R.string.empty_wiki_title)
        tvEmptyViewDescription.setText(R.string.empty_wiki_description)

        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)
        viewModel.getEventsObservable()
            .subscribe(
                this::handleEvent,
                this::defaultHandleException
            )
            .disposeOnDestroy()

        // See comment in viewModel
        viewModel.setLaunchIdParam(intent.getLaunchId())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_wiki, menu)
        favoriteMenuItem = menu.findItem(R.id.action_favorite)
        if (favoriteIconResId != 0) {
            favoriteMenuItem!!.setIcon(favoriteIconResId)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                viewModel.favoriteButtonClick()
                true
            }
            else -> false
        }
    }

    private fun renderState(state: State) {
        favoriteIconResId = if (state.favorite) {
            R.drawable.ic_favorite_red
        } else {
            R.drawable.ic_favorite_white
        }
        favoriteMenuItem?.setIcon(favoriteIconResId)
        if (state.emptyView) {
            vwEmptyWikiPage.visible()
            wbWiki.gone()
        } else {
            vwEmptyWikiPage.gone()
            wbWiki.visible()
            wbWiki.loadUrl(state.wikiPageLink)
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is ShowErrorAlert -> showError(event)
        }
    }

    private fun showError(error: ShowErrorAlert) {
        showToast(error.reason.toString())
    }
}
