package com.aperezsi.diablobuddy.container.di.module

import androidx.lifecycle.ViewModel
import com.aperezsi.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.aperezsi.core.di.presentation.viewmodel.ViewModelKey
import com.aperezsi.diablobuddy.container.presentation.ContainerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContainerViewModel::class)
    abstract fun bindContainerViewModel(containerViewModel: ContainerViewModel): ViewModel
}