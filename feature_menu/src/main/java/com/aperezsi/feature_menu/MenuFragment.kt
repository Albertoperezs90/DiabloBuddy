package com.aperezsi.feature_menu

import androidx.lifecycle.ViewModelProvider
import com.aperezsi.core.framework.base.BaseFragment
import com.aperezsi.diablobuddy.di.application.appComponent
import com.aperezsi.feature_menu.databinding.FragmentMenuBinding
import com.aperezsi.feature_menu.di.DaggerMenuComponent

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel by lazy { ViewModelProvider(this).get(MenuViewModel::class.java) }

    override fun inflate() = FragmentMenuBinding.inflate(layoutInflater)

    override fun initialize() {
        DaggerMenuComponent.factory().create(appComponent()).inject(this)
        viewModel.counter.observe(this, {
            binding.text.text = it.toString()
        })

        binding.button.setOnClickListener {
            viewModel.increaseCounter()
        }
    }
}
