package com.github.domain.interactors

import android.util.Log
import com.github.data.entities.CommentData
import com.github.data.entities.PostData
import com.github.data.entities.UserData
import com.github.data.repositories.CommentsRepository
import com.github.data.repositories.PostRepository
import com.github.data.repositories.UserRepository
import com.github.domain.enteties.Comment
import com.github.domain.enteties.Post
import com.github.scopes.ActivityScope
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

@ActivityScope
class PostInteractor @Inject constructor(
        private val postRepository: PostRepository,
        private val userRepository: UserRepository,
        private val commentsRepository: CommentsRepository
) {

    fun getAllPosts(): Single<List<Post>> {
        return Single.zip(
                postRepository.getPosts(),
                userRepository.getUsers(),
                commentsRepository.getComments(),
                Function3 { posts, users, comments ->
                    joinPostWithUser(posts, users, comments)
                })
    }

    private fun joinPostWithUser(
            posts: List<PostData>,
            users: List<UserData>,
            commentsData: List<CommentData>): List<Post> {
        val resultList = mutableListOf<Post>()

        val commentsMap: Map<Int, List<CommentData>> = commentsData.groupBy { it.postId }
        for (postData in posts) {
            val user = users.find { it.id == postData.userId }
            val userName = user?.name
            val postBody = postData.body
            val postTitle = postData.title
            if (userName != null && postBody != null && postTitle != null) {
                val commentList = commentsMap[postData.id]?.map {
                    Comment(
                            name = it.name,
                            email = it.email,
                            body = it.body
                    )
                } ?: emptyList()
                resultList.add(
                        Post(userName = userName,
                                postBody = postBody,
                                postTitle = postTitle,
                                comments = commentList
                        )
                )
            } else {
                //here could be some kind of analytic event
                Log.d("PostInteractor", "Invalid data")
            }
        }
        return resultList
    }
}