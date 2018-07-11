package com.github.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.github.domain.interactors.PostInteractor
import com.github.presentation.dagger.PostListComponentCreator
import com.github.presentation.dagger.PostListModule
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var postInteractor: PostInteractor
    @Inject
    lateinit var coolString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as PostListComponentCreator)
                .createPostListComponent(PostListModule())
                .inject(this)


        postInteractor
                .loadSomething()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { post ->
                    findViewById<TextView>(R.id.test_view).text = coolString + post.body
                }
    }
}
