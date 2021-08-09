package com.aperezsi.diablobuddy

import android.app.Application
import android.content.Context
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.di.DaggerCoreComponent
import com.aperezsi.diablobuddy.di.application.AppComponent
import com.aperezsi.diablobuddy.di.application.DaggerAppComponent

class DiabloBuddyApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeDaggerComponent()
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create()
    }

    private fun initializeDaggerComponent(): AppComponent = DaggerAppComponent.factory().create(coreComponent, this)

    companion object {
        fun coreComponent(context: Context): CoreComponent {
            return (context.applicationContext as DiabloBuddyApplication).coreComponent
        }
    }
}
