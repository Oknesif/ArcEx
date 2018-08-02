package com.github.data.repositories

import com.github.data.DbDao
import com.github.data.entities.UserData
import com.github.data.remote.Api
import io.reactivex.Single

class UserRepository(
        private val dbDao: DbDao,
        private val api: Api
) {

    fun getUsers(): Single<List<UserData>> {
        return dbDao.getUsers()
                .map { if (it.isEmpty()) throw NoSuchElementException() else it }
                .onErrorResumeNext { api.getUsers().doOnSuccess { users -> dbDao.insertUsers(users) } }
    }
}