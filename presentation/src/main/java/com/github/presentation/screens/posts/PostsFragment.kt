package com.github.presentation.screens.posts

import android.os.Bundle
import android.view.View
import com.github.presentation.R
import com.github.presentation.architecture.components.BaseFragment
import com.github.presentation.architecture.components.Subscriber
import com.github.presentation.architecture.components.ComponentProvider

class PostsFragment : BaseFragment<PostsComponent>() {

    override fun createComponent(
            screensComponent: ComponentProvider,
            savedInstanceState: Bundle?
    ): PostsComponent {
        return screensComponent
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