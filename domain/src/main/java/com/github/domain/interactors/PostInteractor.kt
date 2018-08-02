package com.github.domain.interactors

import android.util.Log
import com.github.data.entities.PostData
import com.github.data.entities.UserData
import com.github.data.repositories.PostRepository
import com.github.data.repositories.UserRepository
import com.github.domain.enteties.Post
import io.reactivex.Single
import io.reactivex.rxkotlin.zipWith

class PostInteractor(
        private val postRepository: PostRepository,
        private val userRepository: UserRepository
) {

    fun getAllPosts(): Single<List<Post>> {
        return postRepository.getPosts()
                .zipWith(userRepository.getUsers()) { posts, users ->
                    joinPostWithUser(posts, users)
                }
    }

    private fun joinPostWithUser(
            posts: List<PostData>,
            users: List<UserData>): List<Post> {
        val resultList = mutableListOf<Post>()
        for (postData in posts) {
            val user = users.find { it.id == postData.userId }
            val userName = user?.name
            val postBody = postData.body
            val userId = user?.id
            if (userName != null && postBody != null && userId != null) {
                resultList.add(Post(userName, postBody, userId.toString()))
            } else {
                //here could be some kind of analytic event
                Log.d("PostInteractor", "Invalid data")
            }
        }
        return resultList
    }
}