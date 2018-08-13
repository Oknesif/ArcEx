package com.github.presentation.screens.post.details

import android.view.View
import android.widget.TextView
import com.github.presentation.R
import com.github.presentation.architecture.components.Subscriber
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class PostDetailsView(
        view: View,
        private val state: Observable<PostDetailsState>
) : Subscriber {

    private val titleView: TextView = view.findViewById(R.id.text_title)
    private val bodyTextView: TextView = view.findViewById(R.id.text_body)

    override fun subscribe(): Disposable {
        return state.subscribe {
            return@subscribe when (it) {
                is PostDetailsState.Show -> {
                    titleView.text = it.post.postTitle
                    bodyTextView.text = it.post.postBody
                }
                else -> {

                }
            }
        }
    }
}