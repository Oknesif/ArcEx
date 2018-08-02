package com.github.presentation.architecture.components

import io.reactivex.disposables.Disposable

interface Subscribable {

    fun subscribe(): Disposable

}