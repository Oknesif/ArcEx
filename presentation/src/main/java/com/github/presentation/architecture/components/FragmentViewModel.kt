package com.github.presentation.architecture.components

import android.arch.lifecycle.ViewModel
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