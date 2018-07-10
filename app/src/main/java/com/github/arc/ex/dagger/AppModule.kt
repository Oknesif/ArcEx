package com.github.arc.ex.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
        private val appContext: Context
) {

    @Provides
    @Singleton
    fun providerAppContext(): Context {
        return appContext
    }
}