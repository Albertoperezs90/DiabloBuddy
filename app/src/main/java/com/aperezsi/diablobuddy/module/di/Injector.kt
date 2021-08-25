package com.aperezsi.diablobuddy.module.di

import com.aperezsi.diablobuddy.module.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.module.splash.presentation.SplashActivity
import com.aperezsi.diablobuddy.shared.di.appComponent

internal fun ContainerActivity.inject() {
    appComponent().appModuleComponent.create(this).inject(this)
}

internal fun SplashActivity.inject() {
    appComponent().appModuleComponent.create(this).inject(this)
}