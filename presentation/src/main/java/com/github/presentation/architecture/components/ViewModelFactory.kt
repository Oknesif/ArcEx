package com.github.presentation.architecture.components

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.screens.posts.PostsViewModel

class ViewModelFactory(
        private val activityComponent: ActivityComponent
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            PostsViewModel::class.java -> PostsViewModel(activityComponent) as T
            else -> throw UnsupportedClassVersionError()
        }
    }
}