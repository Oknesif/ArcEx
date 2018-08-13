package com.github.data.repositories

import com.github.data.DbDao
import com.github.data.entities.PostData
import com.github.data.remote.Api
import io.reactivex.Single

class PostRepository(
        private val dbDao: DbDao,
        private val api: Api
) {

    fun getPost(id: Int): Single<PostData> {
        return dbDao.getPost(id)
    }

    fun getPosts(): Single<List<PostData>> {
        return dbDao.getPosts()
                .map { if (it.isEmpty()) throw NoSuchElementException() else it }
                .onErrorResumeNext { api.getPosts().doOnSuccess { posts -> dbDao.insertPosts(posts) } }
    }
}