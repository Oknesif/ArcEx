package com.github.presentation.activity.dagger

import android.app.Activity
import android.view.LayoutInflater
import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.reactivex.ArcSchedulers
import com.github.presentation.reactivex.ArcSchedulersImp
import com.github.presentation.screens.post.details.PostDetailsComponent
import com.github.presentation.screens.posts.dagger.PostsComponent
import com.github.scopes.AppScope
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

@Module(subcomponents = [PostsComponent::class, PostDetailsComponent::class])
class ActivityModule(
        private val activity: Activity
) {

    @Provides
    @AppScope
    fun provideEventBus(): Subject<AppEvent> = PublishSubject.create()

    @Provides
    @AppScope
    fun provideActivity(): Activity = activity

    @Provides
    @AppScope
    fun provideLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    @AppScope
    fun provideSchedulers(): ArcSchedulers = ArcSchedulersImp()

}