package com.github.presentation.dagger

import android.content.Context
import android.view.LayoutInflater
import com.github.presentation.AppEvent
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
        private val context: Context
) {

    @Provides
    @ActivityScope
    fun provideEventBus(): Subject<AppEvent> = PublishSubject.create()

    @Provides
    @Named("activity")
    @ActivityScope
    fun provideContext(): Context = context

    @Provides
    @ActivityScope
    fun provideLayoutInflater(): LayoutInflater = LayoutInflater.from(context)

    @Provides
    @ActivityScope
    fun provideSchedulers(): ArcSchedulers = ArcSchedulersImp()

}