package com.github.presentation

import io.reactivex.disposables.Disposable

interface Subscribable {

    fun subscribe(): Disposable

}