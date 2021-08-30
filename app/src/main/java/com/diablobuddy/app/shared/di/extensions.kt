package com.diablobuddy.app.shared.di

import com.diablobuddy.app.DiabloBuddyApplication
import com.diablobuddy.app.shared.di.application.AppComponent
import com.diablobuddy.core.framework.base.BaseActivity
import com.diablobuddy.core.framework.base.BaseFragment

fun BaseActivity<*, *, *>.appComponent(): AppComponent {
    return (applicationContext as DiabloBuddyApplication).appComponent
}

fun BaseFragment<*, *, *>.appComponent(): AppComponent {
    return (context!!.applicationContext as DiabloBuddyApplication).appComponent
}