package com.github.data.repositories

import com.github.data.DbDao
import com.github.data.entities.CommentData
import com.github.data.remote.Api
import io.reactivex.Single

class CommentsRepository(
        private val dbDao: DbDao,
        private val api: Api
) {

    fun getComments(postId: Int): Single<List<CommentData>> {
        return dbDao.getComments(postId)
    }

    fun getComments(): Single<List<CommentData>> {
        return dbDao.getComments()
                .map { if (it.isEmpty()) throw NoSuchElementException() else it }
                .onErrorResumeNext { api.getComments().doOnSuccess { comments -> dbDao.insertComments(comments) } }
    }
}