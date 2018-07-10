package com.github.data.remote

import com.github.data.entities.CommentData
import com.github.data.entities.PostData
import com.github.data.entities.UserData
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("users")
    fun getUsers(): Single<List<UserData>>

    @GET("comments")
    fun getComments(): Single<List<CommentData>>

    @GET("posts")
    fun getPosts(): Single<List<PostData>>

}