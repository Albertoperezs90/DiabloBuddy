package com.aperezsi.core.di

import android.app.Application
import android.content.SharedPreferences
import com.aperezsi.core.di.coroutines.CoroutineModule
import com.aperezsi.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.aperezsi.core.di.session.SessionModule
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [CoroutineModule::class, SessionModule::class, ViewModelFactoryModule::class])
interface CoreComponent {

    @get:Named("session")
    val sharedPreferences: SharedPreferences

    val dispatcherProvider: DispatcherProvider

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): CoreComponent
    }
}