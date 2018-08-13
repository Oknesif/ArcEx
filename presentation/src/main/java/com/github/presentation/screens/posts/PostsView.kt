package com.github.presentation.screens.posts

import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.github.presentation.R
import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.architecture.components.Subscriber
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

class PostsView(
        view: View,
        postsComponent: PostsComponent
) : Subscriber {

    @Inject
    lateinit var state: Subject<PostsState>
    @Inject
    lateinit var eventObserver: Observer<AppEvent>

    private val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
    private val progress: ContentLoadingProgressBar = view.findViewById(R.id.progress)
    private val adapter: PostsAdapter = PostsAdapter(
            layoutInflater = LayoutInflater.from(view.context)
    ) { post -> eventObserver.onNext(PostClickEvent(post)) }

    init {
        postsComponent.inject(this)
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun subscribe(): Disposable {
        return state.subscribe {
            return@subscribe when (it) {
                is PostsState.ShowPosts -> {
                    adapter.items.addAll(it.posts)
                    adapter.notifyDataSetChanged()
                    progress.hide()
                    recyclerView.visibility = View.VISIBLE
                }
                is PostsState.Loading -> {
                    progress.show()
                    recyclerView.visibility = View.INVISIBLE
                }
            }
        }
    }
}