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
import com.github.presentation.activity.MainActivity
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.screens.post.details.FragmentViewModel
import io.reactivex.disposables.Disposable

abstract class BaseFragment<T> : Fragment() {

    private var viewDisposable: Disposable? = null
    private var component: T? = null

    abstract fun createViewSubscriber(view: View, component: T): Subscriber

    abstract fun createUseCaseSubscriber(component: T): Subscriber

    abstract fun createComponent(activityComponent: ActivityComponent): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = ViewModelProviders
                .of(this, object : ViewModelProvider.Factory {
                    private val activityComponent = (activity as MainActivity)
                            .getActivityComponent()

                    override fun <R : ViewModel?> create(modelClass: Class<R>): R {
                        val component = createComponent(activityComponent)
                        val useCase = createUseCaseSubscriber(component)
                        return FragmentViewModel(component, useCase) as R
                    }
                })
                .get(FragmentViewModel::class.java)
                .component as T
    }

    @LayoutRes
    abstract fun provideLayoutId(): Int

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