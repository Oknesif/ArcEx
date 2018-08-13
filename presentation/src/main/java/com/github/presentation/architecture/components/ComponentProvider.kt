package com.github.presentation.architecture.components

import com.github.presentation.screens.post.details.PostDetailsComponent
import com.github.presentation.screens.posts.PostsComponent
import io.reactivex.Observable

interface ComponentProvider {

    fun getAppEvents(): Observable<AppEvent>

    fun postsComponent(): PostsComponent.Builder

    fun postDetailsComponent(): PostDetailsComponent.Builder
}
