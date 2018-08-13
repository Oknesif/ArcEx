package com.github.presentation.screens.posts

import com.github.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

@Module
class PostsModule {

    @Provides
    @FragmentScope
    fun provideStateSubject(): Subject<PostsState> = BehaviorSubject.create()
}