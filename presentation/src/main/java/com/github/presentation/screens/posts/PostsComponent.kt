package com.github.presentation.screens.posts

import com.github.scopes.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PostsModule::class])
interface PostsComponent {

    fun getUseCase(): PostsUseCase

    fun inject(postsFragment: PostsView)

    @Subcomponent.Builder
    interface Builder {
        fun postModule(module: PostsModule): Builder
        fun build(): PostsComponent
    }
}