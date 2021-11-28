package com.diablobuddy.feature_skills.di

import androidx.lifecycle.ViewModel
import com.diablobuddy.core.di.presentation.viewmodel.ViewModelFactoryModule
import com.diablobuddy.core.di.presentation.viewmodel.ViewModelKey
import com.diablobuddy.feature_skills.character_select.CharacterSkillsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class CharacterSkillsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterSkillsViewModel::class)
    abstract fun provideCharacterSelectViewModel(characterSkillsViewModel: CharacterSkillsViewModel): ViewModel
}