package com.aperezsi.core.di

import android.app.Application
import android.content.SharedPreferences
import com.aperezsi.core.di.data.DataModule
import com.aperezsi.core.di.presentation.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelFactoryModule::class, DataModule::class])
interface CoreComponent {

    val sharedPreferences: SharedPreferences

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): CoreComponent
    }
}