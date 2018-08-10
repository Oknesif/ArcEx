package com.github.presentation.activity.dagger

interface ActivityComponentCreator {
    fun createActivityComponent(activityModule: ActivityModule): ActivityComponent
}