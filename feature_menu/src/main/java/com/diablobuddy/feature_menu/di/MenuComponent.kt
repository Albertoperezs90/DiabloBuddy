package com.diablobuddy.feature_menu.di

import com.diablobuddy.app.shared.di.application.AppComponent
import com.diablobuddy.app.shared.di.navigator.NavigatorModule
import com.diablobuddy.core.di.presentation.view.BaseFragmentComponent
import com.diablobuddy.core.framework.base.BaseActivity
import com.diablobuddy.feature_menu.presentation.MenuFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MenuModule::class, NavigatorModule::class], dependencies = [AppComponent::class])
interface MenuComponent: BaseFragmentComponent<MenuFragment> {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent, @BindsInstance activity: BaseActivity<*, *, *>): MenuComponent
    }
}