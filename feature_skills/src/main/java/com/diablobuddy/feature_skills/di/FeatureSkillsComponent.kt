package com.diablobuddy.feature_skills.di

import com.aperezsi.diablobuddy.shared.di.application.AppComponent
import com.diablobuddy.feature_skills.character_select.CharacterSelectFragment
import dagger.Component

@Component(modules = [CharacterSelectModule::class], dependencies = [AppComponent::class])
interface FeatureSkillsComponent {

    fun inject(characterSelectFragment: CharacterSelectFragment)

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): FeatureSkillsComponent
    }
}