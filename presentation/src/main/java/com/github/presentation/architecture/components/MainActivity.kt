package com.github.presentation.architecture.components

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.github.presentation.R
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
}
