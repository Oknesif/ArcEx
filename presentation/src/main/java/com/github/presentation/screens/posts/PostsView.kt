package com.github.presentation.screens.posts

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.github.presentation.Subscribable
import com.github.presentation.R
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject

class PostsView(
        view: View,
        inflater: LayoutInflater,
        private val state: Subject<PostsState>
) : Subscribable {
    private val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
    private val progress: View = view.findViewById(R.id.progress)
    private val adapter: PostAdapter = PostAdapter(layoutInflater = inflater)

    init {
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
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