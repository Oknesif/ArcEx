package com.github.arc.ex

import android.app.Application
import com.github.arc.ex.dagger.AppComponent
import com.github.arc.ex.dagger.AppModule
import com.github.arc.ex.dagger.DaggerAppComponent
import com.github.data.dagger.DataModule
import com.github.presentation.architecture.components.ComponentProvider
import com.github.presentation.screens.post.details.PostDetailsComponent
import com.github.presentation.screens.posts.PostsComponent

class ArcExApplication : Application(), ComponentProvider {

    override fun postsComponent(): PostsComponent.Builder {
        return appComponent.postsComponent()
    }

    override fun postDetailsComponent(): PostDetailsComponent.Builder {
        return appComponent.postDetailsComponent()
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this.applicationContext))
                .dataModule(DataModule())
                .build()
    }
}