package com.github.presentation.screens.post.details

import android.view.View
import com.github.presentation.R
import com.github.presentation.activity.dagger.ActivityComponent
import com.github.presentation.architecture.components.BaseFragment
import com.github.presentation.architecture.components.Subscriber

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

    override fun createComponent(activityComponent: ActivityComponent): PostDetailsComponent {
        return activityComponent
                .postDetailsComponent()
                .postDetailsModule(PostDetailsModule()) //TODO I could use params to set them to Module :)
                .build()
    }

    override fun provideLayoutId(): Int = R.layout.post_details_fragment
}
//
//class PostDetailsFragmentss : Fragment() {
//
//    private var viewDisposable: Disposable? = null
//
//    private lateinit var component: PostDetailsComponent
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val activityComponent: ActivityComponent = (activity as MainActivity).getActivityComponent()
//        component = ViewModelProviders
//                .of(this, object : ViewModelProvider.Factory {
//                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                        val postDetailsComponent = activityComponent
//                                .postDetailsComponent()
//                                .postDetailsModule(PostDetailsModule())
//                                .build()
//
//                        return FragmentViewModel(
//                                component = postDetailsComponent,
//                                useCase = postDetailsComponent.getUseCase()
//                        ) as T
//                    }
//                })
//                .get(FragmentViewModel::class.java)
//                .component as PostDetailsComponent
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.post_details_fragment, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val state = component.getState()
//        viewDisposable = PostDetailsView(view, state).subscribe()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        viewDisposable?.dispose()
//    }
//}