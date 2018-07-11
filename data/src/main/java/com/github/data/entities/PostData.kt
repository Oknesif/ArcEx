package com.github.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity
class PostData(
        val userId: Int,
        @PrimaryKey val id: Int,
        val title: String,
        val body: String
)