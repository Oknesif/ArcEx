package com.github.presentation.screens.post.details

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.github.presentation.R
import com.github.presentation.architecture.components.Subscriber
import com.github.presentation.image.loading.AvatarLoader
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PostDetailsView(
        private val view: View,
        component: PostDetailsComponent
) : Subscriber {

    @Inject
    lateinit var avatarLoader: AvatarLoader
    @Inject
    lateinit var state: Observable<PostDetailsState>

    private val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

    init {
        component.inject(this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        recyclerView.requestLayout()
    }

    override fun subscribe(): Disposable {
        return state.subscribe {
            when (it) {
                is PostDetailsState.Show -> {
                    recyclerView.adapter = PostDetailsAdapter(
                            post = it.post,
                            layoutInflater = LayoutInflater.from(view.context),
                            avatarLoader = avatarLoader)
                }
            }
        }
    }
}