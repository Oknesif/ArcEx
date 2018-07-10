package com.github.arc.ex

import android.app.Application
import com.github.arc.ex.dagger.AppModule
import com.github.arc.ex.dagger.DaggerAppComponent
import com.github.data.dagger.DataModule
import com.github.domain.dagger.DomainModule
import com.github.presentation.dagger.PostListComponent
import com.github.presentation.dagger.PostListComponentCreator
import com.github.presentation.dagger.PostListModule

class ArcExApplication : Application(), PostListComponentCreator {

    override fun createPostListComponent(postListModule: PostListModule): PostListComponent {
        return appComponent
                .domainComponent
                .domainModule(DomainModule())
                .dataModule(DataModule())
                .build()
                .postListComponent
                .postModule(postListModule)
                .build()
    }

    val appComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this.applicationContext))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}