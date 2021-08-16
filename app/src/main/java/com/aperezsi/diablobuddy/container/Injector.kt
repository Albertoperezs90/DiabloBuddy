package com.aperezsi.diablobuddy.container

import com.aperezsi.diablobuddy.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.shared.di.appComponent

internal fun ContainerActivity.inject() {
    appComponent().containerComponentFactory.create(this).inject(this)
}