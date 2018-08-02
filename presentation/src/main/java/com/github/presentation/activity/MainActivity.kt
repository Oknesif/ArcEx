package com.github.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.github.presentation.R
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.activity.dagger.ActivityComponentCreator
import com.github.presentation.activity.dagger.ActivityModule
import com.github.presentation.screens.posts.PostsFragment

class MainActivity : AppCompatActivity() {

    val activityComponent: ActivityComponent by lazy {
        (application as ActivityComponentCreator).createActivityComponent(ActivityModule(this))
    }

    private lateinit var container: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.main_container)
        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, PostsFragment())
                .commit()
    }
}
