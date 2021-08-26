package com.aperezsi.core.di.coroutines

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.coroutines.DispatcherProviderImpl
import dagger.Module
import dagger.Provides

@Module
class CoroutineModule {

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}