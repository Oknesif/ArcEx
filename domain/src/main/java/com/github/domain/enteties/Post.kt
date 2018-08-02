package com.github.domain.enteties

data class Post(
        val userName: String,
        val postBody: String,
        val postTitle: String,
        val comments: List<Comment>
)