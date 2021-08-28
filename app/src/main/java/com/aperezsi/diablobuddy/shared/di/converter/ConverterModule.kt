package com.aperezsi.diablobuddy.shared.di.converter

import com.aperezsi.diablobuddy.shared.data.converter.ConverterFactoryProvider
import com.aperezsi.diablobuddy.shared.data.converter.MoshiProvider
import dagger.Module
import dagger.Provides

@Module
class ConverterModule {

    @Provides
    fun provideConverterFactory(): ConverterFactoryProvider {
        return MoshiProvider()
    }
}