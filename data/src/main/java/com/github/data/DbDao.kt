package com.github.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.data.entities.CommentData
import com.github.data.entities.PostData
import com.github.data.entities.UserData
import io.reactivex.Flowable

@Dao
interface DbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserData>)

    @Query("SELECT * FROM userData")
    fun getUsers(): Flowable<List<UserData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentData>)

    @Query("SELECT * FROM commentData")
    fun getComments(): Flowable<List<CommentData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostData>)

    @Query("SELECT * FROM postData")
    fun getPosts(): Flowable<List<PostData>>
}