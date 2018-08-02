package com.github.presentation.screens.posts

import com.github.domain.interactors.PostInteractor
import com.github.presentation.architecture.components.Subscribable
import com.github.presentation.reactivex.ArcSchedulers
import com.github.scopes.FragmentScope
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

class PostsUseCase @Inject constructor(
        private val schedulers: ArcSchedulers,
        private val state: Subject<PostsState>,
        private val postsInteractor: PostInteractor
) : Subscribable {
    override fun subscribe(): Disposable {
        return postsInteractor.getAllPosts()
                .map { PostsState.ShowPosts(it) as PostsState }
                .toObservable()
                .startWith(PostsState.Loading)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.mainThread)
                .subscribe(state::onNext)
    }
}

