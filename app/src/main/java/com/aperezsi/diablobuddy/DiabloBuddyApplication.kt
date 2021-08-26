package com.aperezsi.diablobuddy

import android.app.Application
import com.aperezsi.core.di.CoreComponent
import com.aperezsi.core.di.DaggerCoreComponent
import com.aperezsi.diablobuddy.shared.di.application.AppComponent
import com.aperezsi.diablobuddy.shared.di.application.DaggerAppComponent

open class DiabloBuddyApplication: Application() {

    private var _coreComponent: CoreComponent? = null
    val coreComponent: CoreComponent = _coreComponent ?: buildCoreComponent()

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent = _appComponent ?: buildAppComponent()

    override fun onCreate() {
        super.onCreate()
        buildCoreComponent()
        buildAppComponent()
    }

    private fun buildAppComponent(): AppComponent {
        if (_appComponent == null) {
            _appComponent = DaggerAppComponent.factory().create(coreComponent, this)
        }
        return _appComponent!!
    }

    protected open fun buildCoreComponent(): CoreComponent {
        if (_coreComponent == null) {
            _coreComponent = DaggerCoreComponent.factory().create(this)
        }
        return _coreComponent!!
    }
}
