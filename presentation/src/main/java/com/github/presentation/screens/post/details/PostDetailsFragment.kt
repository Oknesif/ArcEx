package com.github.presentation.screens.post.details

import android.os.Bundle
import android.view.View
import com.github.presentation.R
import com.github.presentation.architecture.components.BaseFragment
import com.github.presentation.architecture.components.Subscriber
import com.github.presentation.architecture.components.ComponentProvider

class PostDetailsFragment : BaseFragment<PostDetailsComponent>() {

    override fun createViewSubscriber(
            view: View,
            component: PostDetailsComponent
    ): Subscriber {
        val state = component.getState()
        return PostDetailsView(view, state)
    }

    override fun createUseCaseSubscriber(
            component: PostDetailsComponent
    ): Subscriber {
        return component.getUseCase()
    }

    override fun createComponent(
            screensComponent: ComponentProvider,
            savedInstanceState: Bundle?
    ): PostDetailsComponent {
        return screensComponent
                .postDetailsComponent()
                .postDetailsModule(PostDetailsModule()) //TODO I could use params to set them to Module :)
                .build()
    }

    override fun provideLayoutId(): Int = R.layout.post_details_fragment
}