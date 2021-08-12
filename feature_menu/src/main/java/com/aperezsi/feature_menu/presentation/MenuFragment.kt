package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.extensions.observe
import com.aperezsi.core.extensions.provideViewModel
import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.feature_menu.databinding.FragmentMenuBinding
import com.aperezsi.feature_menu.inject

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel by lazy { provideViewModel(MenuViewModel::class) }

    override fun inflate() = FragmentMenuBinding.inflate(layoutInflater)

    override fun setUpView() {
        inject()

        observe(viewModel.counter) {
            binding.text.text = it.toString()
        }

        binding.button.setOnClickListener {
            viewModel.increaseCounter()
        }
    }
}
