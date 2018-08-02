package com.github.presentation.screens.posts

import com.github.domain.enteties.Post

sealed class PostsState {

    class ShowPosts(val posts: List<Post>) : PostsState()

    object Loading : PostsState()
}

