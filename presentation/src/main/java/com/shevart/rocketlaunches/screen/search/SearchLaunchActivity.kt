package com.shevart.rocketlaunches.screen.search

import android.os.Bundle
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmActivity
import com.shevart.rocketlaunches.di.component.AppComponent

class SearchLaunchActivity : AbsMvvmActivity<SearchLaunchViewModel>() {
    override fun provideViewModelClass() = SearchLaunchViewModel::class.java

    override fun provideLayoutResId() = R.layout.activity_search_launch

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
