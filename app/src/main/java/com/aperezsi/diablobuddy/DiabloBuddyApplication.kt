package com.aperezsi.diablobuddy

import android.app.Application
import com.aperezsi.diablobuddy.di.application.AppComponent
import com.aperezsi.diablobuddy.di.application.DaggerAppComponent

class DiabloBuddyApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeDaggerComponent()
    }

    override fun onCreate() {
        super.onCreate()
        initializeDaggerComponent()
    }

    private fun initializeDaggerComponent(): AppComponent {
        return DaggerAppComponent.builder().application(this).build()
    }
}
