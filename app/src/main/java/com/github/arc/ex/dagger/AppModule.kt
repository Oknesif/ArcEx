package com.github.arc.ex.dagger

import android.content.Context
import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.reactivex.ArcSchedulers
import com.github.presentation.reactivex.ArcSchedulersImp
import com.github.presentation.screens.post.details.PostDetailsComponent
import com.github.presentation.screens.posts.PostsComponent
import com.github.scopes.AppScope
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

@Module(subcomponents = [PostsComponent::class, PostDetailsComponent::class])
class AppModule(
        private val appContext: Context
) {

    @Provides
    @AppScope
    fun provideAppContext(): Context = appContext

    @Provides
    @AppScope
    fun provideEventBus(): Subject<AppEvent> = PublishSubject.create()

    @Provides
    @AppScope
    fun provideEventObserver(subject: Subject<AppEvent>): Observer<AppEvent> = subject

    @Provides
    @AppScope
    fun provideEventObservable(subject: Subject<AppEvent>): Observable<AppEvent> = subject

    @Provides
    @AppScope
    fun provideSchedulers(): ArcSchedulers = ArcSchedulersImp()
}