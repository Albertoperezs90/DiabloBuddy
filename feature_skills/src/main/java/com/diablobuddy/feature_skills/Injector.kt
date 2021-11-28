package com.diablobuddy.feature_skills

import com.diablobuddy.app.shared.di.appComponent
import com.diablobuddy.feature_skills.character_select.CharacterSkillsFragment
import com.diablobuddy.feature_skills.di.DaggerFeatureSkillsComponent

internal fun CharacterSkillsFragment.inject() {
    DaggerFeatureSkillsComponent.factory().create(appComponent()).inject(this)
}