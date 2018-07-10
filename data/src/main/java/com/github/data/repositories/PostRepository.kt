package com.github.data.repositories

import com.github.data.DbDao
import com.github.data.entities.PostData
import com.github.data.remote.Api
import io.reactivex.Single

class PostRepository(private val dbDao: DbDao,
                     private val api: Api
) {

    fun getUsers(): Single<List<PostData>> {
        return dbDao.getPosts()
                .map { if (it.isEmpty()) throw NoSuchElementException() else it }
                .firstOrError()
                .onErrorResumeNext { api.getPosts().doOnSuccess { dbDao.insertPosts(it) } }
    }
}