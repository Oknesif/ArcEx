package com.github.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class CommentData(
        @PrimaryKey val id: Int,
        val postId: Int,
        val name: String,
        val email: String,
        val body: String
)