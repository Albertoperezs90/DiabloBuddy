package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.core.framework.collect
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.feature_menu.R
import com.aperezsi.feature_menu.databinding.FragmentMenuBinding
import com.aperezsi.feature_menu.inject
import com.aperezsi.feature_menu.presentation.state.MenuEvent
import com.aperezsi.feature_menu.presentation.state.MenuViewState

class MenuFragment: BaseFragment<FragmentMenuBinding, MenuViewModel, MenuViewState>() {

    override val viewModel: MenuViewModel by lazy { provideViewModel(MenuViewModel::class) }

    override fun inflate() = FragmentMenuBinding.inflate(layoutInflater)

    override fun injectDependencies() {
        inject()
        collect(viewModel.currentSeason) {
            binding.seasonTextView.text = getString(R.string.season_menu, it.toString())
        }
    }

    override fun initialize() {
        viewModel.onEvent(MenuEvent.Initialize)
    }

    override fun render(viewState: MenuViewState) {
        when (viewState) {
            is MenuViewState.DrawMenu -> binding.circularMenu.setMenu(viewState.menuConfig)
        }
    }
}
