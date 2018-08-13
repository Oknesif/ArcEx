package com.github.domain.enteties

data class Post(
        val id: Int,
        val userName: String,
        val postBody: String,
        val postTitle: String,
        val comments: List<Comment>
)