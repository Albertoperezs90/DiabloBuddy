package com.diablobuddy.app

import android.app.Application
import com.diablobuddy.core.di.CoreComponent
import com.diablobuddy.app.shared.di.application.AppComponent
import com.diablobuddy.app.shared.di.application.DaggerAppComponent
import com.diablobuddy.core.di.DaggerCoreComponent

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