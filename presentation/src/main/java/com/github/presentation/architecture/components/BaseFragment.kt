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
import com.github.presentation.screens.post.details.FragmentViewModel
import io.reactivex.disposables.Disposable

abstract class BaseFragment<T> : Fragment() {

    private var viewDisposable: Disposable? = null
    private var component: T? = null

    abstract fun createComponent(
            screensComponent: ComponentProvider,
            savedInstanceState: Bundle?
    ): T

    @LayoutRes
    abstract fun provideLayoutId(): Int

    abstract fun createViewSubscriber(view: View, component: T): Subscriber

    abstract fun createUseCaseSubscriber(component: T): Subscriber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = ViewModelProviders
                .of(this, object : ViewModelProvider.Factory {
                    private val componentProvider = (activity?.application as ComponentProvider)

                    override fun <R : ViewModel?> create(modelClass: Class<R>): R {
                        val component = createComponent(componentProvider, savedInstanceState)
                        val useCase = createUseCaseSubscriber(component)
                        return FragmentViewModel(component, useCase) as R
                    }
                })
                .get(FragmentViewModel::class.java)
                .component as T
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(provideLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDisposable = createViewSubscriber(view, component!!).subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }
}