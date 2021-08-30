package com.diablobuddy.app.shared.di.converter

import com.diablobuddy.app.shared.data.converter.ConverterFactoryProvider
import com.diablobuddy.app.shared.data.converter.MoshiProvider
import dagger.Module
import dagger.Provides

@Module
class ConverterModule {

    @Provides
    fun provideConverterFactory(): ConverterFactoryProvider {
        return MoshiProvider()
    }
}