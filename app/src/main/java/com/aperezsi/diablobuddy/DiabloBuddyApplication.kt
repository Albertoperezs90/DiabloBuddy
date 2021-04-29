package com.aperezsi.diablobuddy

import android.app.Application
import com.aperezsi.diablobuddy.di.application.AppComponent
import com.aperezsi.diablobuddy.di.application.DaggerAppComponent

class DiabloBuddyApplication: Application() {

    val appComponent: AppComponent by lazy {
        initializeDaggerComponent()
    }

    private fun initializeDaggerComponent(): AppComponent = DaggerAppComponent.factory().create(this)
}
