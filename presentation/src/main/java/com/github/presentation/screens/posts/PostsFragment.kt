package com.github.presentation.screens.posts

import android.os.Bundle
import android.view.View
import com.github.presentation.R
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.architecture.components.BaseFragment
import com.github.presentation.architecture.components.Subscriber

class PostsFragment : BaseFragment<PostsComponent>() {

    override fun createComponent(
            activityComponent: ActivityComponent,
            savedInstanceState: Bundle?
    ): PostsComponent {
        return activityComponent
                .postsComponent()
                .build()
    }

    override fun provideLayoutId(): Int = R.layout.posts_fragment

    override fun createViewSubscriber(view: View, component: PostsComponent): Subscriber {
        return PostsView(view, component)
    }

    override fun createUseCaseSubscriber(component: PostsComponent): Subscriber {
        return component.getUseCase()
    }

}