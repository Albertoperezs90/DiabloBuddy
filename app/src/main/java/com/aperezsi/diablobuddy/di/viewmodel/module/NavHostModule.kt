package com.aperezsi.diablobuddy.di.viewmodel.module

import androidx.lifecycle.ViewModel
import com.aperezsi.diablobuddy.di.viewmodel.ViewModelKey
import com.aperezsi.diablobuddy.view.NavHostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class NavHostModule {

    @Binds
    @IntoMap
    @ViewModelKey(NavHostViewModel::class)
    abstract fun bindsNavHostViewModel(impl: NavHostViewModel): ViewModel
}