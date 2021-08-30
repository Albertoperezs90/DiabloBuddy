package com.diablobuddy.app.shared.di.application

import android.app.Application
import com.diablobuddy.app.module.di.AppModuleComponent
import com.diablobuddy.app.shared.di.converter.ConverterModule
import com.diablobuddy.app.shared.di.logger.LoggerModule
import com.diablobuddy.app.shared.di.network.NetworkModule
import com.diablobuddy.app.shared.di.storage.StorageModule
import com.diablobuddy.app.shared.di.tracker.TrackerModule
import com.diablobuddy.app.shared.storage.AppPreferences
import com.diablobuddy.core.di.CoreComponent
import com.diablobuddy.core.interfaces.logger.Logger
import com.diablobuddy.core.interfaces.tracker.EventTracker
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(
    modules = [ConverterModule::class, LoggerModule::class, NetworkModule::class, StorageModule::class, TrackerModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    val appModuleComponent: AppModuleComponent.Factory

    val appPreferences: AppPreferences
    val logger: Logger
    val eventTracker: EventTracker
    val dispatcherProvider: DispatcherProvider

    val retrofit: Retrofit

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent, @BindsInstance application: Application): AppComponent
    }
}