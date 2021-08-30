package com.diablobuddy.core.di.coroutines

import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import com.diablobuddy.core.utilities.coroutines.DispatcherProviderImpl
import dagger.Module
import dagger.Provides

@Module
class CoroutineModule {

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}