package com.github.presentation.activity.dagger

interface ActivityComponentCreator {
    fun provideAppComponent(): ActivityComponent
}