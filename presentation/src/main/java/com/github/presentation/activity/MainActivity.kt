package com.github.presentation.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.github.presentation.R
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.activity.dagger.ActivityComponentCreator
import com.github.presentation.activity.dagger.ActivityModule
import com.github.presentation.screens.posts.PostsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var container: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.main_container)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, PostsFragment())
                    .commit()
        }
    }

    fun getActivityComponent(): ActivityComponent = ViewModelProviders
            .of(this, Factory())
            .get(ActivityViewModel()::class.java)
            .activityComponent

    private inner class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ActivityViewModel() as T
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class ActivityViewModel : ViewModel() {
        val activityComponent: ActivityComponent by lazy {
            (application as ActivityComponentCreator).createActivityComponent(ActivityModule())
        }
    }
}


