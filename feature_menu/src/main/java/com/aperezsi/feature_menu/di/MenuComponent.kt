package com.aperezsi.feature_menu.di

import com.aperezsi.core.di.view.BaseFragmentComponent
import com.aperezsi.diablobuddy.di.application.AppComponent
import com.aperezsi.feature_menu.presentation.MenuFragment
import dagger.Component

@Component(modules = [MenuModule::class], dependencies = [AppComponent::class])
interface MenuComponent : BaseFragmentComponent<MenuFragment> {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MenuComponent
    }
}