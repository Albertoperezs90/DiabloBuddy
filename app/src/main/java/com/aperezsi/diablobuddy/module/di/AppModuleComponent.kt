package com.aperezsi.diablobuddy.module.di

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.diablobuddy.module.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.module.splash.presentation.SplashActivity
import com.aperezsi.diablobuddy.shared.di.navigator.NavigatorModule
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ApiModule::class, NavigatorModule::class, ViewModelModule::class])
interface AppModuleComponent {

    fun inject(splashActivity: SplashActivity)
    fun inject(containerActivity: ContainerActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance activity: BaseActivity<*, *, *>): AppModuleComponent
    }
}