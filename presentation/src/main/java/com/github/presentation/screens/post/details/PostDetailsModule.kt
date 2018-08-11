package com.github.presentation.screens.post.details

import com.github.scopes.FragmentScope
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

@Module
class PostDetailsModule {

    @Provides
    @FragmentScope
    fun provideStateSubject(): Subject<PostDetailsState> = BehaviorSubject.create()

    @Provides
    @FragmentScope
    fun provideStateObservable(
            subject: Subject<PostDetailsState>
    ): Observable<PostDetailsState> = subject
}