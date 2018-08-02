package com.github.presentation.screens.posts

import com.github.domain.interactors.PostInteractor
import com.github.presentation.reactivex.ArcSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

class Binder @Inject constructor(
        private val schedulers: ArcSchedulers,
        private val state: Subject<PostsState>,
        private val postsInteractor: PostInteractor
) {
    fun bind(): Disposable {
        return postsInteractor.getAllPosts()
                .map { PostsState.ShowPosts(it) as PostsState }
                .toObservable()
                .startWith(PostsState.Loading())
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.mainThread)
                .subscribe(state::onNext)
    }
}

