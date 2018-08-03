package com.github.presentation.screens.posts

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.github.presentation.R
import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.architecture.components.Subscribable
import com.github.presentation.image.loading.AvatarLoader
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject

class PostsView(
        view: View,
        inflater: LayoutInflater,
        private val state: Subject<PostsState>,
        private val eventObserver: Observer<AppEvent>
) : Subscribable {
    private val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
    private val progress: View = view.findViewById(R.id.progress)
    private val adapter: PostsAdapter = PostsAdapter(
            layoutInflater = inflater
    ) { post ->
        //        eventObserver.onNext(post)
    }

    init {
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun subscribe(): Disposable {
        return state.subscribe {
            val unit = when (it) {
                is PostsState.ShowPosts -> {
                    adapter.items.addAll(it.posts)
                    adapter.notifyDataSetChanged()
                    progress.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
                is PostsState.Loading -> {
                    progress.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
            }
        }
    }
}