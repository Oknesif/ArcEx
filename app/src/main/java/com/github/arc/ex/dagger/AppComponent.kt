package com.github.arc.ex.dagger

import com.github.data.dagger.DataModule
import com.github.domain.dagger.DomainModule
import com.github.presentation.dagger.ActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun appComponent(): AppComponent

    fun activityComponent(): ActivityComponent.Builder
}