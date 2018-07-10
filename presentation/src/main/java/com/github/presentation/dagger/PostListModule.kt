package com.github.presentation.dagger

import com.github.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class PostListModule {

    @Provides
    @ActivityScope
    fun providerString(): String {
        return "Hey from postlist module"
    }
}