package com.aperezsi.diablobuddy.di.application

import android.app.Application
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.core.logger.Logger
import com.aperezsi.core.tracker.EventTracker
import com.aperezsi.diablobuddy.DiabloBuddyApplication
import com.aperezsi.diablobuddy.di.logger.LoggerModule
import com.aperezsi.diablobuddy.di.tracker.TrackerModule
import com.aperezsi.diablobuddy.di.view.NavHostComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [LoggerModule::class, TrackerModule::class], dependencies = [CoreComponent::class])
interface AppComponent {

    val navHostComponentFactory: NavHostComponent.Factory
    val logger: Logger
    val eventTracker: EventTracker

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent, @BindsInstance application: Application): AppComponent
    }
}

fun BaseFragment<*, *>.appComponent(): AppComponent {
    return (context!!.applicationContext as DiabloBuddyApplication).appComponent
}