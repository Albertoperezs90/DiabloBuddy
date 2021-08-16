package com.aperezsi.diablobuddy.shared.di

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.diablobuddy.DiabloBuddyApplication
import com.aperezsi.diablobuddy.shared.di.application.AppComponent

fun BaseActivity<*, *>.appComponent(): AppComponent {
    return (applicationContext as DiabloBuddyApplication).appComponent
}

fun BaseFragment<*, *>.appComponent(): AppComponent {
    return (context!!.applicationContext as DiabloBuddyApplication).appComponent
}