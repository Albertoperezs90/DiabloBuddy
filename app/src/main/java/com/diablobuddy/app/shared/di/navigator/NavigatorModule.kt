package com.diablobuddy.app.shared.di.navigator

import com.diablobuddy.app.shared.navigator.Navigator
import com.diablobuddy.core.framework.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class NavigatorModule {

    @Provides
    fun provideNavigator(activity: BaseActivity<*, *, *>): Navigator {
        return Navigator(activity)
    }
}