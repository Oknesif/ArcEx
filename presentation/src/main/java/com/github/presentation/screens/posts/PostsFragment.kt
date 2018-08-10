package com.github.presentation.screens.posts

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.presentation.R
import com.github.presentation.activity.MainActivity
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.architecture.components.ViewModelFactory
import io.reactivex.disposables.Disposable

class PostsFragment : Fragment() {

    private var viewDisposable: Disposable? = null
    private lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityComponent: ActivityComponent = (activity as MainActivity).getActivityComponent()
        viewModel = ViewModelProviders
                .of(this, ViewModelFactory(activityComponent))
                .get(PostsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDisposable = PostsView(view, viewModel.postsComponent).subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }
}