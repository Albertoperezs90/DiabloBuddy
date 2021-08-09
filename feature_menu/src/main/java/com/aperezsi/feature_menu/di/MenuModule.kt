package com.aperezsi.feature_menu.di

import androidx.lifecycle.ViewModel
import com.aperezsi.core.di.ViewModelKey
import com.aperezsi.core.di.ViewModelFactoryModule
import com.aperezsi.feature_menu.MenuViewModel
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule {

    @Binds
    @ViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(menuViewModel: MenuViewModel): ViewModel

}