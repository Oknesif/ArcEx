package com.github.presentation.screens.post.details

import com.github.scopes.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PostDetailsModule::class])
interface PostDetailsComponent {
    fun inject(postDetailsFragment: PostDetailsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun postDetailsModule(module: PostDetailsModule): Builder
        fun build(): PostDetailsComponent
    }
}