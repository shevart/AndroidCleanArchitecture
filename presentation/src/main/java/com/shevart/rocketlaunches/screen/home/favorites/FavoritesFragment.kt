package com.shevart.rocketlaunches.screen.home.favorites

import android.os.Bundle
import android.view.View
import com.shevart.rocketlaunches.R
import com.shevart.rocketlaunches.base.mvvm.AbsMvvmFragment
import com.shevart.rocketlaunches.di.component.AppComponent
import kotlinx.android.synthetic.main.layout_empty_view.*

class FavoritesFragment : AbsMvvmFragment<FavoritesViewModel>() {
    override fun provideViewModelClass() = FavoritesViewModel::class.java

    override fun provideLayoutResId() = R.layout.fragment_favorites

    override fun initializeInjection(appComponent: AppComponent) = appComponent.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivEmptyViewLogo.setImageResource(R.drawable.empty_favorites)
        tvEmptyViewTitle.setText(R.string.empty_favorites_title)
        tvEmptyViewDescription.setText(R.string.empty_favorites_description)
    }

}
