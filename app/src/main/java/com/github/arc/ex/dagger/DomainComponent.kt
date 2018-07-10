package com.github.arc.ex.dagger

import com.github.data.dagger.DataModule
import com.github.domain.dagger.DomainModule
import com.github.presentation.dagger.PostListComponent
import com.github.scopes.DomainScope
import dagger.Subcomponent

@DomainScope
@Subcomponent(modules = [DomainModule::class, DataModule::class])
interface DomainComponent {

    var postListComponent: PostListComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun domainModule(domainModule: DomainModule): Builder
        fun dataModule(dataModule: DataModule): Builder
        fun build(): DomainComponent
    }
}