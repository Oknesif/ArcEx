package com.github.arc.ex

import android.app.Application
import com.github.arc.ex.dagger.AppComponent
import com.github.arc.ex.dagger.AppModule
import com.github.arc.ex.dagger.DaggerAppComponent
import com.github.data.dagger.DataModule
import com.github.domain.dagger.DomainModule
import com.github.presentation.dagger.ActivityComponent
import com.github.presentation.dagger.ActivityComponentCreator
import com.github.presentation.dagger.ActivityModule

class ArcExApplication : Application(), ActivityComponentCreator {

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
                .domainModule(DomainModule())
                .build()
    }
}