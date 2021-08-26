package com.aperezsi.core.di

import android.app.Application
import com.aperezsi.core.di.coroutines.CoroutineFakeModule
import com.aperezsi.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.aperezsi.core.di.session.SessionModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelFactoryModule::class, SessionModule::class, CoroutineFakeModule::class])
interface CoreFakeComponent: CoreComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): CoreComponent
    }
}