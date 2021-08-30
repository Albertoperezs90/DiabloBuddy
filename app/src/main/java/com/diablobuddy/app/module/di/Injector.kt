package com.diablobuddy.app.module.di

import com.diablobuddy.app.module.container.presentation.ContainerActivity
import com.diablobuddy.app.module.splash.presentation.SplashActivity
import com.diablobuddy.app.shared.di.appComponent

internal fun ContainerActivity.inject() {
    appComponent().appModuleComponent.create(this).inject(this)
}

internal fun SplashActivity.inject() {
    appComponent().appModuleComponent.create(this).inject(this)
}