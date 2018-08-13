package com.github.presentation.screens.post.details

import com.github.domain.interactors.PostsInteractor
import com.github.presentation.architecture.components.Subscriber
import com.github.presentation.reactivex.ArcSchedulers
import com.github.scopes.FragmentScope
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

@FragmentScope
class PostDetailsUseCase @Inject constructor(
        private val states: Subject<PostDetailsState>,
        private val interactor: PostsInteractor,
        private val postId: Int,
        private val schedulers: ArcSchedulers
) : Subscriber {

    override fun subscribe(): Disposable {
        return interactor.getPost(postId)
                .map { PostDetailsState.Show(post = it) as PostDetailsState }
                .toObservable()
                .startWith(PostDetailsState.Loading)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.mainThread)
                .subscribe(states::onNext)
    }
}