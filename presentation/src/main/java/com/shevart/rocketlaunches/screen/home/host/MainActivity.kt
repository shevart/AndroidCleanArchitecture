package com.shevart.rocketlaunches.screen.home.host

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmActivity
import com.shevart.rocketlaunches.di.component.AppComponent
import com.shevart.rocketlaunches.screen.home.favorites.FavoritesFragment
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Screen
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Screen.FavoritesScreen
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.Screen.LaunchesScreen
import com.shevart.rocketlaunches.screen.home.host.MainScreenViewModel.State
import com.shevart.rocketlaunches.screen.home.launches.LaunchesListFragment
import com.shevart.rocketlaunches.util.observeLiveDataForceNonNull
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbsMvvmActivity<MainScreenViewModel>() {
    override fun provideLayoutResId() = R.layout.activity_main

    override fun provideViewModelClass() = MainScreenViewModel::class.java

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()// do via theme
        bnHomeNavi.setOnNavigationItemSelectedListener(this::onBottomNaviItemSelected)

        observeLiveDataForceNonNull(viewModel.getStateLiveData(), this::renderState)
    }

    private fun renderState(state: State) {
        val fragment = getScreenFragment(screen = state.screen)
        displayFragment(fragment)
    }

    private fun onBottomNaviItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_launches -> viewModel.showLaunchesScreen()
            R.id.action_favorites -> viewModel.showFavorites()
            else -> return false
        }
        return true
    }

    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flHomeContainer, fragment)
            .commit()
    }

    private fun getScreenFragment(screen: Screen): Fragment =
        when (screen) {
            LaunchesScreen -> LaunchesListFragment()
            FavoritesScreen -> FavoritesFragment()
        }
}
