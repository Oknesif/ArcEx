package com.github.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.data.entities.CommentData
import com.github.data.entities.PostData
import com.github.data.entities.UserData
import io.reactivex.Single

@Dao
interface DbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserData>)

    @Query("SELECT * FROM userData")
    fun getUsers(): Single<List<UserData>>

    @Query("SELECT * FROM userData where id = :id LIMIT 1")
    fun getUser(id: Int): Single<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<CommentData>)

    @Query("SELECT * FROM commentData")
    fun getComments(): Single<List<CommentData>>

    @Query("SELECT * FROM commentData where postId = :postId")
    fun getComments(postId: Int): Single<List<CommentData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostData>)

    @Query("SELECT * FROM postData")
    fun getPosts(): Single<List<PostData>>

    @Query("SELECT * from postData where id = :id LIMIT 1")
    fun getPost(id: Int): Single<PostData>
}