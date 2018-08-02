package com.github.presentation.screens.posts

import android.view.View
import com.github.presentation.BaseFragment
import com.github.presentation.MainActivity
import com.github.presentation.R
import com.github.presentation.Subscribable
import com.github.presentation.screens.posts.dagger.PostsModule
import io.reactivex.subjects.Subject
import javax.inject.Inject

class PostsFragment : BaseFragment() {
    @Inject
    lateinit var postsUseCase: PostsUseCase
    @Inject
    lateinit var state: Subject<PostsState>

    override fun onInject() {
        (activity as MainActivity).activityComponent
                .postsComponent()
                .postModule(PostsModule())
                .build()
                .inject(this)
    }

    override fun createView(view: View): Subscribable {
        return PostsView(view, layoutInflater, state)
    }

    override fun createUseCase(): Subscribable {
        return postsUseCase
    }

    override fun provideLayoutId(): Int {
        return R.layout.posts_fragment
    }
}