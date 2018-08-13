package com.github.presentation.activity.dagger

import com.github.presentation.activity.MainActivity
import com.github.presentation.screens.post.details.PostDetailsComponent
import com.github.presentation.screens.posts.PostsComponent
import com.github.scopes.AppScope
import dagger.Subcomponent

@AppScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun postsComponent(): PostsComponent.Builder

    fun postDetailsComponent(): PostDetailsComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(module: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}