package com.aperezsi.core.di.coroutines

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.coroutines.DispatcherProviderFake
import dagger.Module
import dagger.Provides

@Module
class CoroutineFakeModule {

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderFake()
    }
}