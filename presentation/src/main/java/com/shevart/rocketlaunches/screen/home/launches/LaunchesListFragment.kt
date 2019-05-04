package com.shevart.rocketlaunches.screen.home.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmFragment
import com.shevart.rocketlaunches.di.component.AppComponent

class LaunchesListFragment : AbsMvvmFragment<LaunchesListViewModel>() {
    override fun provideLayoutResId() = R.layout.fragment_launches_list

    override fun provideViewModelClass() = LaunchesListViewModel::class.java

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
