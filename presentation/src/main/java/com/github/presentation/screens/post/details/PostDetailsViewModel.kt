package com.github.presentation.screens.post.details

import android.arch.lifecycle.ViewModel
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.screens.posts.PostClickEvent
import com.github.scopes.FragmentScope
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

@FragmentScope
class PostDetailsViewModel(
        activityComponent: ActivityComponent
) : ViewModel() {

    @Inject
    lateinit var events: Observable<AppEvent>
    @Inject
    lateinit var stateSubject: Subject<PostDetailsState>

    private var disposable: Disposable

    val postDetailsComponent: PostDetailsComponent = activityComponent
            .postDetailsComponent()
            .postDetailsModule(PostDetailsModule())
            .build()

    init {
        postDetailsComponent.inject(this)
        disposable = createUseCase()
    }

    private fun createUseCase(): Disposable {
        return events.ofType(PostClickEvent::class.java)
                .firstOrError()
                .map { it.post }
                .map { PostDetailsState.Show(post = it) }
                .subscribe(stateSubject::onNext)
    }

    override fun onCleared() {
        disposable.dispose()
    }
}