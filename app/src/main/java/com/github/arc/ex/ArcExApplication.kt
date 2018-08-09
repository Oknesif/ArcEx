package com.github.arc.ex

import android.app.Application
import com.github.arc.ex.dagger.AppComponent
import com.github.arc.ex.dagger.AppModule
import com.github.arc.ex.dagger.DaggerAppComponent
import com.github.data.dagger.DataModule
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.activity.dagger.ActivityComponentCreator
import com.github.presentation.activity.dagger.ActivityModule

class ArcExApplication : Application() {

    override fun createActivityComponent(activityModule: ActivityModule): ActivityComponent {
        return appComponent
                .activityComponent()
                .activityModule(activityModule)
                .build()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this.applicationContext))
                .dataModule(DataModule())
                .build()
    }
}