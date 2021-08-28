package com.aperezsi.diablobuddy.module.di

import androidx.lifecycle.ViewModel
import com.aperezsi.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.aperezsi.core.di.presentation.viewmodel.ViewModelKey
import com.aperezsi.diablobuddy.module.container.presentation.ContainerViewModel
import com.aperezsi.diablobuddy.module.splash.presentation.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContainerViewModel::class)
    abstract fun bindContainerViewModel(containerViewModel: ContainerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}