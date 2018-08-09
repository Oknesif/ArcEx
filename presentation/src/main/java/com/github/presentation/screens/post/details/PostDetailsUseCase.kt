package com.github.presentation.screens.post.details

import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.architecture.components.Subscribable
import com.github.presentation.screens.posts.PostClickEvent
import com.github.scopes.FragmentScope
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

@FragmentScope
class PostDetailsUseCase @Inject constructor(
        private val events: Subject<AppEvent>,
        private val stateSubject: Subject<PostDetailsState>
) : Subscribable {
    override fun subscribe(): Disposable {
        return events.ofType(PostClickEvent::class.java)
                .firstOrError()
                .map { it.post }
                .map {
                    PostDetailsState.Show(post = it)
                }
                .subscribe(stateSubject::onNext)
    }
}