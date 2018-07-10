package com.github.presentation.dagger

import com.github.presentation.MainActivity
import com.github.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [PostListModule::class])
interface PostListComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun postModule(postListModule: PostListModule): Builder
        fun build(): PostListComponent
    }
}