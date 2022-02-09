package com.diablobuddy.feature_skills.character_select

import androidx.appcompat.widget.SearchView
import com.diablobuddy.core.framework.base.BaseFragment
import com.diablobuddy.core.framework.provideViewModel
import com.diablobuddy.core.utilities.clickhandler.setOnDebounceClickListener
import com.diablobuddy.feature_skills.character_select.state.CharacterSkillsViewState
import com.diablobuddy.feature_skills.databinding.FragmentCharacterskillsBinding
import com.diablobuddy.feature_skills.inject

class CharacterSkillsFragment: BaseFragment<FragmentCharacterskillsBinding, CharacterSkillsViewModel, CharacterSkillsViewState>() {

    override val viewModel: CharacterSkillsViewModel by lazy { provideViewModel(CharacterSkillsViewModel::class) }

    private var sceneState = 0

    override fun inflate(): FragmentCharacterskillsBinding = FragmentCharacterskillsBinding.inflate(layoutInflater)

    override fun injectDependencies() {
        inject()
    }

    override fun initialize() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // do nothing
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.orEmpty().isNotEmpty()) {
                    binding.motionScene.transitionToEnd()
                } else {
                    binding.motionScene.transitionToStart()
                }
                return true
            }
        })
    }

    override fun render(viewState: CharacterSkillsViewState) {
        // Do nothing
    }
}