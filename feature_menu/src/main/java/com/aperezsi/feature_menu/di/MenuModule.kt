package com.aperezsi.feature_menu.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aperezsi.core.di.ViewModelFactoryModule
import com.aperezsi.core.di.ViewModelKey
import com.aperezsi.core.di.ViewModelProviderFactory
import com.aperezsi.feature_menu.MenuViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class MenuModule {

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(menuViewModel: MenuViewModel): ViewModel

}