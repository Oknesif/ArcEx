package com.github.presentation.architecture.components

import io.reactivex.disposables.Disposable

interface Subscriber {

    fun subscribe(): Disposable

}