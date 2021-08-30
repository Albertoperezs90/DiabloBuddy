package com.diablobuddy.feature_menu

import com.diablobuddy.app.shared.di.appComponent
import com.diablobuddy.core.framework.base.BaseActivity
import com.diablobuddy.feature_menu.di.DaggerMenuComponent
import com.diablobuddy.feature_menu.presentation.MenuFragment

internal fun MenuFragment.inject() {
    DaggerMenuComponent.factory().create(appComponent(), requireActivity() as BaseActivity<*, *, *>).inject(this)
}