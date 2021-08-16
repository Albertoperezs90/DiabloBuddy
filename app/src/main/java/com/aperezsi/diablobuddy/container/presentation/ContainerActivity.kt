package com.aperezsi.diablobuddy.container.presentation

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.diablobuddy.container.inject
import com.aperezsi.diablobuddy.databinding.ActivityContainerBinding

class ContainerActivity : BaseActivity<ActivityContainerBinding, ContainerViewModel>() {

    override val viewModel: ContainerViewModel by lazy { provideViewModel(ContainerViewModel::class) }

    override fun inflate(): ActivityContainerBinding = ActivityContainerBinding.inflate(layoutInflater)

    override fun setUpView() {
        inject()
        viewModel.initialize()
    }
}