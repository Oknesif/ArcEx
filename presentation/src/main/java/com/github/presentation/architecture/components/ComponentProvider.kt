package com.github.presentation.architecture.components

import com.github.presentation.screens.post.details.PostDetailsComponent
import com.github.presentation.screens.posts.PostsComponent

interface ComponentProvider {

    fun postsComponent(): PostsComponent.Builder

    fun postDetailsComponent(): PostDetailsComponent.Builder
}
