package com.github.presentation.dagger

interface PostListComponentCreator {
    fun createPostListComponent(postListModule: PostListModule): PostListComponent
}