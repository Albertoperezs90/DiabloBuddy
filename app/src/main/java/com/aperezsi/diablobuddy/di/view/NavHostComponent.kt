package com.aperezsi.diablobuddy.di.view

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.diablobuddy.view.NavHostActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface NavHostComponent {

    fun inject(navHostActivity: NavHostActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: BaseActivity<*>): NavHostComponent
    }
}