package com.shevart.rocketlaunches.base.mvvm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.shevart.rocketlaunches.base.screen.BaseActivity
import com.shevart.rocketlaunches.util.subscribeOnIoObserveOnMain
import javax.inject.Inject

abstract class AbsMvvmActivity<VM : ViewModel> : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: VM

    abstract fun provideViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isFinishing) {
            initViewModel()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(provideViewModelClass())
        subscribeForErrors(viewModel)
    }

    private fun subscribeForErrors(viewModel: VM) {
        if (viewModel is BaseViewModel) {
            viewModel.defaultConsumerSubject
                .subscribeOnIoObserveOnMain()
                .subscribe(
                    this::onViewModelError,
                    this::defaultHandleException
                )
                .disposeOnDestroy()
        }
    }

    protected open fun onViewModelError(e: Throwable) {
        defaultHandleException(e)
    }
}