package com.diablobuddy.feature_skills.di

import com.diablobuddy.app.shared.di.application.AppComponent
import com.diablobuddy.feature_skills.character_select.CharacterSkillsFragment
import dagger.Component

@Component(modules = [CharacterSkillsModule::class], dependencies = [AppComponent::class])
interface FeatureSkillsComponent {

    fun inject(characterSkillsFragment: CharacterSkillsFragment)

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): FeatureSkillsComponent
    }
}