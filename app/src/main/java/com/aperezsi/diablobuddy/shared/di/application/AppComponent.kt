package com.aperezsi.diablobuddy.shared.di.application

import android.app.Application
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.logger.Logger
import com.aperezsi.core.tracker.EventTracker
import com.aperezsi.diablobuddy.container.di.ContainerComponent
import com.aperezsi.diablobuddy.shared.di.data.HttpModule
import com.aperezsi.diablobuddy.shared.di.logger.LoggerModule
import com.aperezsi.diablobuddy.shared.di.tracker.TrackerModule
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(
    modules = [AppModule::class, HttpModule::class, LoggerModule::class, TrackerModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    val containerComponentFactory: ContainerComponent.Factory

    val retrofit: Retrofit
    val sessionPreferences: SessionPreferences
    val logger: Logger
    val eventTracker: EventTracker

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent, @BindsInstance application: Application): AppComponent
    }
}