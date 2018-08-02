package com.github.presentation.activity.dagger

import android.app.Activity
import android.view.LayoutInflater
import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.reactivex.ArcSchedulers
import com.github.presentation.reactivex.ArcSchedulersImp
import com.github.presentation.screens.posts.dagger.PostsComponent
import com.github.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Named

@Module(subcomponents = [PostsComponent::class])
class ActivityModule(
        private val activity: Activity
) {

    @Provides
    @ActivityScope
    fun provideEventBus(): Subject<AppEvent> = PublishSubject.create()

    @Provides
    @ActivityScope
    fun provideActivity(): Activity = activity

    @Provides
    @ActivityScope
    fun provideLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    @ActivityScope
    fun provideSchedulers(): ArcSchedulers = ArcSchedulersImp()

}