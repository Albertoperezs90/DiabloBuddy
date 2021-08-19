package com.aperezsi.diablobuddy.shared.di.application

import android.app.Application
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.interfaces.tracker.EventTracker
import com.aperezsi.diablobuddy.container.di.ContainerComponent
import com.aperezsi.diablobuddy.shared.di.data.NetworkModule
import com.aperezsi.diablobuddy.shared.di.logger.LoggerModule
import com.aperezsi.diablobuddy.shared.di.tracker.TrackerModule
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named

@Component(
    modules = [AppModule::class, NetworkModule::class, LoggerModule::class, TrackerModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    val containerComponentFactory: ContainerComponent.Factory

    val sessionPreferences: SessionPreferences
    val logger: Logger
    val eventTracker: EventTracker

    @get:Named("api")
    val retrofit: Retrofit

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent, @BindsInstance application: Application): AppComponent
    }
}