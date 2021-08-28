package com.aperezsi.diablobuddy.shared.di.application

import android.app.Application
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.interfaces.tracker.EventTracker
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.diablobuddy.module.di.AppModuleComponent
import com.aperezsi.diablobuddy.shared.di.converter.ConverterModule
import com.aperezsi.diablobuddy.shared.di.logger.LoggerModule
import com.aperezsi.diablobuddy.shared.di.network.NetworkModule
import com.aperezsi.diablobuddy.shared.di.storage.StorageModule
import com.aperezsi.diablobuddy.shared.di.tracker.TrackerModule
import com.aperezsi.diablobuddy.shared.storage.AppPreferences
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