package com.github.presentation.screens.posts.dagger

import com.github.presentation.screens.posts.PostsFragment
import com.github.scopes.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PostsModule::class])
interface PostsComponent {

    fun inject(postsFragment: PostsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun postModule(module: PostsModule): Builder
        fun build(): PostsComponent
    }
}