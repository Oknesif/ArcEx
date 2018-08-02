package com.github.presentation.architecture.components

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
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