package com.diablobuddy.feature_skills.di

import androidx.lifecycle.ViewModel
import com.diablobuddy.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.diablobuddy.core.di.presentation.viewmodel.ViewModelKey
import com.diablobuddy.feature_skills.character_select.CharacterSelectViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class CharacterSelectModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterSelectViewModel::class)
    abstract fun provideCharacterSelectViewModel(characterSelectViewModel: CharacterSelectViewModel): ViewModel
}