package com.github.presentation.screens.post.details

import com.github.domain.enteties.Post

sealed class PostDetailsState {
    object Loading : PostDetailsState()
    class Show(val post: Post) : PostDetailsState()
}