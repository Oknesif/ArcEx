package com.github.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.data.entities.*

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