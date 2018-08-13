package com.github.arc.ex.dagger

import com.github.data.dagger.DataModule
import com.github.presentation.architecture.components.ComponentProvider
import com.github.presentation.screens.post.details.PostDetailsComponent
import com.github.presentation.screens.posts.PostsComponent
import com.github.scopes.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent: ComponentProvider {

    override fun postsComponent(): PostsComponent.Builder

    override fun postDetailsComponent(): PostDetailsComponent.Builder
}