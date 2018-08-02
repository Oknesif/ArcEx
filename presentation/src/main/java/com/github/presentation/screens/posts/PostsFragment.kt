package com.github.presentation.screens.posts

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.presentation.FragmentModel
import com.github.presentation.MainActivity
import com.github.presentation.R
import com.github.presentation.ViewModelFactory
import com.github.presentation.screens.posts.dagger.PostsModule
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

class PostsFragment : Fragment() {

    private var viewSubscription: Disposable? = null
    @Inject
    lateinit var binder: Binder
    @Inject
    lateinit var state: Subject<PostsState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).activityComponent
                .postsComponent()
                .postModule(PostsModule())
                .build()
                .inject(this)

        ViewModelProviders
                .of(this, ViewModelFactory())
                .get(FragmentModel::class.java)
                .init { createBinder().bind() }
    }

    fun createBinder(): Binder {
        return binder
    }

    fun createView(view: View): PostsView {
        return PostsView(view, layoutInflater, state)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewSubscription = createView(view).bind()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewSubscription?.dispose()
    }

}