package com.github.presentation.screens.post.details

import android.view.View
import com.github.presentation.R
import com.github.presentation.activity.MainActivity
import com.github.presentation.architecture.components.BaseFragment
import com.github.presentation.architecture.components.Subscribable
import io.reactivex.subjects.Subject
import javax.inject.Inject

class PostDetailsFragment : BaseFragment() {

    @Inject
    lateinit var stateObservable: Subject<PostDetailsState>
    @Inject
    lateinit var postDetailsUseCase: PostDetailsUseCase

    override fun onInject() {
        (activity as MainActivity).activityComponent
                .postsDetailsComponent()
                .postDetailsModule(PostDetailsModule())
                .build()
                .inject(this)
    }

    override fun provideLayoutId(): Int = R.layout.post_details_fragment

    override fun createUseCase(): Subscribable = postDetailsUseCase

    override fun createView(view: View): Subscribable = PostDetailsView(view, stateObservable)
}