package com.shevart.rocketlaunches.base.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.shevart.rocketlaunches.core.app.RocketLaunchesApp
import com.shevart.rocketlaunches.core.util.log.RLogger
import com.shevart.rocketlaunches.di.component.AppComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {
    private val onDestroyDisposables = CompositeDisposable()
    private val onViewDestroyDisposables = CompositeDisposable()
    private val onStopDisposables = CompositeDisposable()

    @LayoutRes
    protected abstract fun provideLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjection(getAppComponent())
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(provideLayoutResId(), container, false)
    }

    override fun onStop() {
        onStopDisposables.clear()
        super.onStop()
    }

    override fun onDestroy() {
        onDestroyDisposables.clear()
        super.onDestroy()
    }

    override fun onDestroyView() {
        onViewDestroyDisposables.clear()
        super.onDestroyView()
    }

    protected open fun initializeInjection(appComponent: AppComponent) {
    }

    private fun getAppComponent(): AppComponent {
        return (activity?.application as RocketLaunchesApp).getAppComponent()
    }

    protected fun defaultHandleException(e: Throwable) {
        RLogger.log(e)
    }

    @Suppress("unused")
    protected fun Disposable.disposeOnStop() =
        this.apply {
            onStopDisposables.add(this)
        }

    protected fun Disposable.disposeOnDestroy() =
        this.apply {
            onDestroyDisposables.add(this)
        }

    protected fun Disposable.disposeOnDestroyView() =
        this.apply {
            onViewDestroyDisposables.add(this)
        }

}