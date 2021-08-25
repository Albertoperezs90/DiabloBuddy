package com.aperezsi.diablobuddy.shared.di.navigator

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.diablobuddy.shared.navigator.Navigator
import dagger.Module
import dagger.Provides

@Module
class NavigatorModule {

    @Provides
    fun provideNavigator(activity: BaseActivity<*, *, *>): Navigator {
        return Navigator(activity)
    }
}