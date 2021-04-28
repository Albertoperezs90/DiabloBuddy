package com.aperezsi.diablobuddy.di.viewmodel.module

import dagger.Module
import dagger.Provides

@Module
class CounterProvider {

    @Provides
    fun provideCounter(): Int = 2
}