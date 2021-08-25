package com.aperezsi.diablobuddy.module.di

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.diablobuddy.module.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.module.splash.presentation.SplashActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class, ApiModule::class])
interface AppModuleComponent {

    fun inject(splashActivity: SplashActivity)
    fun inject(containerActivity: ContainerActivity)

    @Subcomponent.Factory
    interface Factory {

        fun create(@BindsInstance activity: BaseActivity<*, *, *>): AppModuleComponent
    }
}