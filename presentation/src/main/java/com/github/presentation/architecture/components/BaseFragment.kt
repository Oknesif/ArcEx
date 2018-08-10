package com.github.presentation.architecture.components

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.Disposable

abstract class BaseFragment<T : ViewModel> : Fragment() {

    private var viewDisposable: Disposable? = null

    /**
     * UseCase disposable lives longer than ViewDisposable
     */
    abstract fun createViewModel(): T

    abstract fun createView(view: View): Subscribable

    fun getViewModel(): T {
        return ViewModelProviders
                .of(this, Factory())
                .get(ViewModel::class.java) as T
    }

    @LayoutRes
    abstract fun provideLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(provideLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDisposable = createView(view).subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }

    private inner class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return createViewModel() as T
        }
    }
}