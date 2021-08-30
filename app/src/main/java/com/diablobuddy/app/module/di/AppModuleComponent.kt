package com.diablobuddy.app.module.di

import com.diablobuddy.app.module.container.presentation.ContainerActivity
import com.diablobuddy.app.module.splash.presentation.SplashActivity
import com.diablobuddy.app.shared.data.ApiModule
import com.diablobuddy.app.shared.di.navigator.NavigatorModule
import com.diablobuddy.core.framework.base.BaseActivity
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