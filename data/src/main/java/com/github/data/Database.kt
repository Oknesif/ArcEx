package com.github.data

import android.arch.persistence.room.RoomDatabase

abstract class Database : RoomDatabase() {

    abstract fun createDbDao(): DbDao

}