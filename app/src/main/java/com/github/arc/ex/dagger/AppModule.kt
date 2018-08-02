package com.github.arc.ex.dagger

import android.content.Context
import com.github.presentation.activity.dagger.ActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(subcomponents = [ActivityComponent::class])
class AppModule(
        private val appContext: Context
) {

    @Provides
    @Named("application")
    fun providerAppContext(): Context {
        return appContext
    }
}