package com.aperezsi.diablobuddy

import android.app.Application
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.di.DaggerCoreComponent
import com.aperezsi.diablobuddy.shared.di.application.AppComponent
import com.aperezsi.diablobuddy.shared.di.application.DaggerAppComponent

class AppInjector(private val application: Application) {

    private var _coreComponent: CoreComponent? = null
    val coreComponent: CoreComponent = _coreComponent ?: buildCoreComponent()

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent = _appComponent ?: buildAppComponent()

    fun buildComponents() {
        buildCoreComponent()
        buildAppComponent()
    }

    private fun buildAppComponent(): AppComponent {
        if (_appComponent == null) {
            _appComponent = DaggerAppComponent.factory().create(coreComponent, application)
        }
        return _appComponent!!
    }

    private fun buildCoreComponent(): CoreComponent {
        if (_coreComponent == null) {
            _coreComponent = DaggerCoreComponent.factory().create(application)
        }
        return _coreComponent!!
    }
}