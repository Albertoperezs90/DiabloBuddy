package com.aperezsi.diablobuddy.di.viewmodel.module

import androidx.lifecycle.ViewModelProvider
import com.aperezsi.diablobuddy.di.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}