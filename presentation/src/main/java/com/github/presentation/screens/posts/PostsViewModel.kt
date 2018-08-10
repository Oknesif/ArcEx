package com.github.presentation.screens.posts

import android.arch.lifecycle.ViewModel
import com.github.domain.interactors.PostInteractor
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.reactivex.ArcSchedulers
import com.github.presentation.screens.posts.dagger.PostsComponent
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import javax.inject.Inject

class PostsViewModel(
        activityComponent: ActivityComponent
) : ViewModel() {

    @Inject
    lateinit var schedulers: ArcSchedulers
    @Inject
    lateinit var postsInteractor: PostInteractor
    @Inject
    lateinit var state: Subject<PostsState>

    val postsComponent: PostsComponent = activityComponent
            .postsComponent()
            .build()

    private var disposable: Disposable

    init {
        postsComponent.inject(this)
        disposable = createUseCase()
    }

    override fun onCleared() {
        disposable.dispose()
    }

    private fun createUseCase(): Disposable {
        return postsInteractor.getAllPosts()
                .map { PostsState.ShowPosts(it) as PostsState }
                .toObservable()
                .startWith(PostsState.Loading)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.mainThread)
                .subscribe(state::onNext)

    }
}

