package com.github.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.data.entities.CommentData
import com.github.data.entities.PostData
import com.github.data.entities.UserData

@Database(
        entities = [
            CommentData::class,
            PostData::class,
            UserData::class
        ],
        version = 1)
abstract class Database : RoomDatabase() {

    abstract fun createDbDao(): DbDao

}