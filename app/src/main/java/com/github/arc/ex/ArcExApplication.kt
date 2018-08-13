package com.github.arc.ex

import android.app.Application
import com.github.arc.ex.dagger.AppComponent
import com.github.arc.ex.dagger.AppModule
import com.github.arc.ex.dagger.DaggerAppComponent
import com.github.data.dagger.DataModule
import com.github.presentation.architecture.components.AppComponentHolder

class ArcExApplication : Application(), AppComponentHolder {

    override val componentProvider: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this.applicationContext))
                .dataModule(DataModule())
                .build()
    }
}