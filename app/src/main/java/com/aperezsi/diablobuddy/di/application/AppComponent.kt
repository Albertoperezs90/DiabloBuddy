package com.aperezsi.diablobuddy.di.application

import android.app.Application
import com.aperezsi.diablobuddy.di.view.NavHostComponent
import com.aperezsi.diablobuddy.di.viewmodel.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class])
interface AppComponent {

    fun navHostComponent(): NavHostComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }
}