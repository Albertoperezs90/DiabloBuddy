package com.diablobuddy.feature_menu.di

import androidx.lifecycle.ViewModel
import com.diablobuddy.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.diablobuddy.core.di.presentation.viewmodel.ViewModelKey
import com.diablobuddy.feature_menu.presentation.MenuViewModel
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