package com.diablobuddy.feature_skills.character_select

import com.diablobuddy.core.framework.base.BaseFragment
import com.diablobuddy.core.framework.provideViewModel
import com.diablobuddy.feature_skills.character_select.state.CharacterSelectViewState
import com.diablobuddy.feature_skills.databinding.FragmentCharacterselectBinding
import com.diablobuddy.feature_skills.inject

class CharacterSelectFragment: BaseFragment<FragmentCharacterselectBinding, CharacterSelectViewModel, CharacterSelectViewState>() {

    override val viewModel: CharacterSelectViewModel by lazy { provideViewModel(CharacterSelectViewModel::class) }

    override fun inflate(): FragmentCharacterselectBinding = FragmentCharacterselectBinding.inflate(layoutInflater)

    override fun injectDependencies() {
        inject()
    }

    override fun initialize() {
        // Do nothing
    }

    override fun render(viewState: CharacterSelectViewState) {
        // Do nothing
    }
}