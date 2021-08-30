package com.diablobuddy.app

import android.app.Application
import com.diablobuddy.app.shared.di.application.AppComponent

open class DiabloBuddyApplication: Application() {

    private lateinit var appInjector: AppInjector

    val appComponent: AppComponent by lazy {
        appInjector.appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appInjector = AppInjector(this)
        appInjector.buildComponents()
    }
}
