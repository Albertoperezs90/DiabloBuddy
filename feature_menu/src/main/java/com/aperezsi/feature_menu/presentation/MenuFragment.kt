package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.feature_menu.databinding.FragmentMenuBinding
import com.aperezsi.feature_menu.inject

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel by lazy { provideViewModel(MenuViewModel::class) }

    override fun inflate() = FragmentMenuBinding.inflate(layoutInflater)

    override fun setUpView() {
        inject()

        viewModel.initialize()
    }
}
