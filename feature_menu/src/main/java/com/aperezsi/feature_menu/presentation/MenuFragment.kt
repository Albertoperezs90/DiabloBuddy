package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.core.framework.observe
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.feature_menu.R
import com.aperezsi.feature_menu.databinding.FragmentMenuBinding
import com.aperezsi.feature_menu.inject
import com.google.android.material.slider.Slider

class MenuFragment: BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel by lazy { provideViewModel(MenuViewModel::class) }

    override fun inflate() = FragmentMenuBinding.inflate(layoutInflater)

    override fun setUpView() {
        inject()

        observe(viewModel.currentSeason) {
            binding.seasonTextView.text = getString(R.string.season_menu, it.toString())
        }

        binding.slider.addOnChangeListener { slider, value, fromUser ->
            binding.circularMenu.updateAngle(value)
        }
    }
}
