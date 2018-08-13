package com.github.presentation.architecture.components

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.github.presentation.R
import com.github.presentation.screens.post.details.POST_ID
import com.github.presentation.screens.post.details.PostDetailsFragment
import com.github.presentation.screens.posts.PostClickEvent
import com.github.presentation.screens.posts.PostsFragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * It is a single Activity
 */
class MainActivity : AppCompatActivity() {

    private lateinit var events: Observable<AppEvent>
    private var routerDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.main_container, PostsFragment())
                    .commit()
        }

        events = (application as AppComponentHolder)
                .componentProvider
                .getAppEvents()
    }

    override fun onStart() {
        super.onStart()
        routerDisposable = events
                .subscribe {
                    when (it) {
                        is PostClickEvent -> {
                            val fragment: Fragment = PostDetailsFragment().apply {
                                val bundle = Bundle()
                                bundle.putInt(POST_ID, it.post.id)
                                arguments = bundle
                            }
                            supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.main_container, fragment)
                                    .addToBackStack(null)
                                    .commit()
                        }
                    }
                }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        routerDisposable?.dispose()
    }
}
