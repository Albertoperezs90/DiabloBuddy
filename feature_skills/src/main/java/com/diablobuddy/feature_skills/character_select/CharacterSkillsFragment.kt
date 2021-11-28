package com.diablobuddy.feature_skills.character_select

import com.diablobuddy.core.framework.base.BaseFragment
import com.diablobuddy.core.framework.provideViewModel
import com.diablobuddy.feature_skills.character_select.state.CharacterSkillsViewState
import com.diablobuddy.feature_skills.databinding.FragmentCharacterskillsBinding
import com.diablobuddy.feature_skills.inject

class CharacterSkillsFragment: BaseFragment<FragmentCharacterskillsBinding, CharacterSkillsViewModel, CharacterSkillsViewState>() {

    override val viewModel: CharacterSkillsViewModel by lazy { provideViewModel(CharacterSkillsViewModel::class) }

    override fun inflate(): FragmentCharacterskillsBinding = FragmentCharacterskillsBinding.inflate(layoutInflater)

    override fun injectDependencies() {
        inject()
    }

    override fun initialize() {
        // Do nothing
    }

    override fun render(viewState: CharacterSkillsViewState) {
        // Do nothing
    }
}