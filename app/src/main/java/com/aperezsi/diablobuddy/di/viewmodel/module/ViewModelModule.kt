package com.aperezsi.diablobuddy.di.viewmodel.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aperezsi.diablobuddy.di.viewmodel.ViewModelKey
import com.aperezsi.diablobuddy.di.viewmodel.ViewModelProviderFactory
import com.aperezsi.diablobuddy.view.NavHostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [CounterProvider::class])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NavHostViewModel::class)
    abstract fun bindsNavHostViewModel(impl: NavHostViewModel): ViewModel
}