package com.aperezsi.feature_menu

import com.aperezsi.diablobuddy.di.application.appComponent
import com.aperezsi.feature_menu.di.DaggerMenuComponent
import com.aperezsi.feature_menu.presentation.MenuFragment

internal fun MenuFragment.inject() {
    DaggerMenuComponent.factory().create(appComponent()).inject(this)
}