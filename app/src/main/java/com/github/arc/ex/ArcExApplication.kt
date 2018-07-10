package com.github.arc.ex

import android.app.Application
import com.github.arc.ex.dagger.AppModule
import com.github.arc.ex.dagger.DaggerAppComponent
import com.github.data.dagger.DataModule
import com.github.domain.dagger.DomainModule

class ArcExApplication : Application() {

    val appComponent = lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this.applicationContext))
                .domainModule(DomainModule())
                .dataModule(DataModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()

    }
}