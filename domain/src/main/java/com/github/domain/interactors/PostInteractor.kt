package com.github.domain.interactors

import com.github.data.repositories.PostRepository
import com.github.data.repositories.UserRepository
import com.github.domain.enteties.Post
import io.reactivex.Single

class PostInteractor(
        private val postRepository: PostRepository,
        private val userRepository: UserRepository
) {

    fun loadSomething(): Single<Post> {
        return postRepository.getPosts()
                .flatMap { posts ->
                    userRepository.getUsers()
                            .map { users ->
                                Post(users[0].name, posts[0].body)
                            }
                }
    }
}