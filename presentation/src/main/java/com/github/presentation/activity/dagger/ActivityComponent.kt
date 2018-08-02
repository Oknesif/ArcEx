package com.github.presentation.activity.dagger

import com.github.presentation.activity.MainActivity
import com.github.presentation.screens.posts.dagger.PostsComponent
import com.github.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun postsComponent(): PostsComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(module: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}