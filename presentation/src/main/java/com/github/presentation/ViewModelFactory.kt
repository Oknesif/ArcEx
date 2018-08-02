package com.github.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.reactivex.disposables.Disposable

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            FragmentModel::class.java -> FragmentModel() as T
            else -> throw UnsupportedClassVersionError()
        }
    }
}

class FragmentModel : ViewModel() {
    private var disposable: Disposable? = null

    fun init(createUseCase: () -> Disposable) {
        if (disposable == null) {
            disposable = createUseCase()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}


abstract class BaseFragment : Fragment() {

    private var viewDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * UseCase disposable lives longer than ViewDisposable
     */
    abstract fun subscribeUseCase(): Disposable

    abstract fun subscribeView(): Disposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDisposable = subscribeView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }
}