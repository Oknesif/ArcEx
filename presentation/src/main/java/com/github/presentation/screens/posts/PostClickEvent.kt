package com.github.presentation.screens.posts

import com.github.domain.enteties.Post
import com.github.presentation.architecture.components.AppEvent

class PostClickEvent(val post: Post) : AppEvent