package com.github.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.domain.enteties.Post

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val some =  Post("sldfj")
    }
}
