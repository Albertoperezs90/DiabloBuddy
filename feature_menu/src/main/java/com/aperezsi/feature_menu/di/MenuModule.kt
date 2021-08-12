package com.aperezsi.feature_menu.di

import androidx.lifecycle.ViewModel
import com.aperezsi.core.di.viewmodel.ViewModelFactoryModule
import com.aperezsi.core.di.viewmodel.ViewModelKey
import com.aperezsi.feature_menu.presentation.MenuViewModel
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