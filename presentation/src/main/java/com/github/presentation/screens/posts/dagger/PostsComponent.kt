package com.github.presentation.screens.posts.dagger

import com.github.presentation.screens.posts.PostsView
import com.github.presentation.screens.posts.PostsViewModel
import com.github.scopes.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PostsModule::class])
interface PostsComponent {

    fun inject(postsFragment: PostsViewModel)
    fun inject(postsFragment: PostsView)

    @Subcomponent.Builder
    interface Builder {
        fun postModule(module: PostsModule): Builder
        fun build(): PostsComponent
    }
}