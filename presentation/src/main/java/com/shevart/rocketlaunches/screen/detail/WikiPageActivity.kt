package com.shevart.rocketlaunches.screen.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmActivity
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.util.getWikiPageUrl
import com.shevart.rocketlaunches.util.setForWiki
import kotlinx.android.synthetic.main.activity_favorite_wiki_page.*

class WikiPageActivity : AbsMvvmActivity<WikiPageViewModel>() {
    private lateinit var favoriteMenuItem: MenuItem

    override fun provideViewModelClass() = WikiPageViewModel::class.java

    override fun provideLayoutResId() = R.layout.activity_favorite_wiki_page

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.wiki)

        wbWiki.setForWiki()
        wbWiki.loadUrl(intent.getWikiPageUrl())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_wiki, menu)
        favoriteMenuItem = menu.findItem(R.id.action_favorite)
        return true
    }
}
