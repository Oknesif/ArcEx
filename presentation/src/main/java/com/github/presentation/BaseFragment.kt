package com.github.presentation

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private var viewDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInject()
        ViewModelProviders.of(this, ViewModelFactory())
                .get(FragmentModel::class.java)
                .init { createUseCase().subscribe() }
    }

    /**
     * UseCase disposable lives longer than ViewDisposable
     */
    abstract fun createUseCase(): Subscribable

    abstract fun createView(view: View): Subscribable

    abstract fun onInject()

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
}