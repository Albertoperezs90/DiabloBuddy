package com.aperezsi.diablobuddy.di.application

import android.app.Application
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.diablobuddy.di.view.NavHostComponent
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [CoreComponent::class])
interface AppComponent {

    val navHostComponentFactory: NavHostComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent, @BindsInstance application: Application): AppComponent
    }
}