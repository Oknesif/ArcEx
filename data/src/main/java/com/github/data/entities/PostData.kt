package com.github.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class PostData(
        @PrimaryKey val id: Int,
        val userId: Int?,
        val title: String?,
        val body: String?
)