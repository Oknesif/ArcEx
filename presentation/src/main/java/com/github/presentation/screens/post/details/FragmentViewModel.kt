package com.github.presentation.screens.post.details

import android.arch.lifecycle.ViewModel
import com.github.presentation.architecture.components.Subscriber
import io.reactivex.disposables.Disposable

class FragmentViewModel<T>(
        val component: T,
        useCase: Subscriber
) : ViewModel() {

    private val disposable: Disposable = useCase.subscribe()

    override fun onCleared() {
        disposable.dispose()
    }
}