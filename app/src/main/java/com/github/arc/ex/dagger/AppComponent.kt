package com.github.arc.ex.dagger

import com.github.data.dagger.DataModule
import com.github.domain.dagger.DomainModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
@Singleton
interface AppComponent {
    fun inject(some: Any)
}