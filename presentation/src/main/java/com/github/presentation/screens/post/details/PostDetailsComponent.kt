package com.github.presentation.screens.post.details

import com.github.scopes.FragmentScope
import dagger.Subcomponent
import io.reactivex.Observable

@FragmentScope
@Subcomponent(modules = [PostDetailsModule::class])
interface PostDetailsComponent {

    fun getUseCase(): PostDetailsUseCase

    fun inject(postDetailsView: PostDetailsView)

    @Subcomponent.Builder
    interface Builder {
        fun postDetailsModule(module: PostDetailsModule): Builder
        fun build(): PostDetailsComponent
    }
}