package com.shevart.rocketlaunches.base.screen

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.shevart.rocketlaunches.core.app.RocketLaunchesApp
import com.shevart.rocketlaunches.core.util.log.RLogger
import com.shevart.rocketlaunches.di.component.AppComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@Suppress("DeprecatedCallableAddReplaceWith")
abstract class BaseActivity : AppCompatActivity() {
    private val onDestroyDisposables = CompositeDisposable()
    private val onStopDisposables = CompositeDisposable()

    @LayoutRes
    abstract fun provideLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(provideLayoutResId())
        initializeInjection(getAppComponent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }

    protected fun defaultHandleException(e: Throwable) {
        RLogger.log(e)
    }

    @Deprecated("Do not use this method in BaseActivity sub-classes!")
    final override fun setContentView(layoutResID: Int) {
        throw UnsupportedOperationException("You cannot use this method!")
    }

    @Deprecated("Do not use this method in BaseActivity sub-classes!")
    final override fun setContentView(view: View?) {
        throw UnsupportedOperationException("You cannot use this method!")
    }

    protected open fun initializeInjection(appComponent: AppComponent) {
    }

    protected fun Disposable.disposeOnStop() = this.apply {
        onStopDisposables.add(this)
    }

    protected fun Disposable.disposeOnDestroy() = this.apply {
        onDestroyDisposables.add(this)
    }

    protected fun Disposable.addTo(d: CompositeDisposable) = this.apply {
        d.add(this)
    }

    private fun getAppComponent(): AppComponent {
        return (application as RocketLaunchesApp).getAppComponent()
    }
}