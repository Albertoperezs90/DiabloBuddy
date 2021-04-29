package com.aperezsi.diablobuddy.di.view

import com.aperezsi.diablobuddy.view.NavHostActivity
import com.aperezsi.diablobuddy.view.base.BaseActivity
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