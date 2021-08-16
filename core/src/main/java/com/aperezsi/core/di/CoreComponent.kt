package com.aperezsi.core.di

import android.app.Application
import android.content.SharedPreferences
import com.aperezsi.core.di.session.SessionModule
import com.aperezsi.core.di.presentation.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [ViewModelFactoryModule::class, SessionModule::class])
interface CoreComponent {

    @get:Named("session")
    val sharedPreferences: SharedPreferences

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): CoreComponent
    }
}