package com.github.presentation.reactivex

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ArcSchedulers {

    val computation: Scheduler

    val io: Scheduler

    val mainThread: Scheduler

}

class ArcSchedulersImp : ArcSchedulers {
    override val computation: Scheduler = Schedulers.computation()

    override val io: Scheduler = Schedulers.io()

    override val mainThread: Scheduler = AndroidSchedulers.mainThread()

}