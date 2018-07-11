package com.github.arc.ex.dagger

import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun domainComponent(): DomainComponent.Builder

}