package com.aperezsi.diablobuddy.container.di

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.diablobuddy.container.di.module.DataModule
import com.aperezsi.diablobuddy.container.di.module.ViewModelModule
import com.aperezsi.diablobuddy.container.presentation.ContainerActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class, DataModule::class])
interface ContainerComponent {

    fun inject(containerActivity: ContainerActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: BaseActivity<*, *>): ContainerComponent
    }
}