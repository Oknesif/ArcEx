package com.github.presentation.screens.post.details

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

class PostDetailsFragment : Fragment() {

    private var viewDisposable: Disposable? = null
    private lateinit var viewModel: PostDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityComponent: ActivityComponent = (activity as MainActivity).getActivityComponent()
        viewModel = ViewModelProviders
                .of(this, ViewModelFactory(activityComponent))
                .get(PostDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.post_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDisposable = PostDetailsView(view, viewModel.postDetailsComponent).subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDisposable?.dispose()
    }
}