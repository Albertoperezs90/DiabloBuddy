package com.diablobuddy.feature_skills

import com.diablobuddy.app.shared.di.appComponent
import com.diablobuddy.feature_skills.character_select.CharacterSelectFragment
import com.diablobuddy.feature_skills.di.DaggerFeatureSkillsComponent

internal fun CharacterSelectFragment.inject() {
    DaggerFeatureSkillsComponent.factory().create(appComponent()).inject(this)
}