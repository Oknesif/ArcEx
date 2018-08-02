package com.github.presentation.dagger

interface ActivityComponentCreator {
    fun createActivityComponent(activityModule: ActivityModule): ActivityComponent
}