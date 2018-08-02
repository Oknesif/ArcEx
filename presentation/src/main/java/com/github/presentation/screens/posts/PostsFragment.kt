package com.github.presentation.screens.posts

import android.view.View
import com.github.presentation.R
import com.github.presentation.activity.MainActivity
import com.github.presentation.architecture.components.AppEvent
import com.github.presentation.architecture.components.BaseFragment
import com.github.presentation.architecture.components.Subscribable
import com.github.presentation.image.loading.AvatarLoader
import com.github.presentation.screens.posts.dagger.PostsModule
import io.reactivex.subjects.Subject
import javax.inject.Inject

class PostsFragment : BaseFragment() {
    @Inject
    lateinit var postsUseCase: PostsUseCase
    @Inject
    lateinit var state: Subject<PostsState>
    @Inject
    lateinit var avatarLoader: AvatarLoader
    @Inject
    lateinit var events: Subject<AppEvent>

    override fun onInject() {
        (activity as MainActivity).activityComponent
                .postsComponent()
                .postModule(PostsModule())
                .build()
                .inject(this)
    }

    override fun createView(view: View): Subscribable {
        return PostsView(
                view = view,
                inflater = layoutInflater,
                state = state,
                avatarLoader = avatarLoader,
                eventObserver = events)
    }

    override fun createUseCase(): Subscribable {
        return postsUseCase
    }

    override fun provideLayoutId(): Int {
        return R.layout.posts_fragment
    }
}