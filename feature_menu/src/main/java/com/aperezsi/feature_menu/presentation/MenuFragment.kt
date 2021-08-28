package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.framework.base.BaseFragment
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
    }

    override fun initialize() {
        viewModel.onEvent(MenuEvent.Initialize)
    }

    override fun render(viewState: MenuViewState) {
        when (viewState) {
            is MenuViewState.DrawSeason -> binding.seasonTextView.text = getString(R.string.season_menu, viewState.seasonIndex.toString())
            is MenuViewState.DrawMenu -> binding.circularMenu.setMenu(viewState.menuConfig)
        }
    }
}
