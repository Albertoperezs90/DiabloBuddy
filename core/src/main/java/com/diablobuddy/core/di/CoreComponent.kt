package com.diablobuddy.core.di

import android.app.Application
import android.content.SharedPreferences
import com.diablobuddy.core.di.coroutines.CoroutineModule
import com.diablobuddy.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.diablobuddy.core.di.storage.PreferencesModule
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CoroutineModule::class, PreferencesModule::class, ViewModelFactoryModule::class])
interface CoreComponent {

    val sharedPreferences: SharedPreferences

    val dispatcherProvider: DispatcherProvider

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): CoreComponent
    }
}