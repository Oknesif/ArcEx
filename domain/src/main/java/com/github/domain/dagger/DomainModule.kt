package com.github.domain.dagger

import com.github.data.repositories.PostRepository
import com.github.data.repositories.UserRepository
import com.github.domain.interactors.PostInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providePostInteractor(
            postRepository: PostRepository,
            userRepository: UserRepository
    ): PostInteractor {
        return PostInteractor(postRepository, userRepository)
    }
}