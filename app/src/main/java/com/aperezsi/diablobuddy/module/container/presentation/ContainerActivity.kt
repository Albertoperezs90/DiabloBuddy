package com.aperezsi.diablobuddy.module.container.presentation

import androidx.navigation.findNavController
import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.diablobuddy.R
import com.aperezsi.diablobuddy.databinding.ActivityContainerBinding
import com.aperezsi.diablobuddy.module.container.presentation.state.ContainerViewState
import com.aperezsi.diablobuddy.module.di.inject

class ContainerActivity: BaseActivity<ActivityContainerBinding, ContainerViewModel, ContainerViewState>() {

    override val viewModel: ContainerViewModel by lazy { provideViewModel(ContainerViewModel::class) }

    override fun inflate(): ActivityContainerBinding = ActivityContainerBinding.inflate(layoutInflater)

    override fun injectDependencies() {
        inject()
    }

    override fun initialize() {
        findNavController(R.id.navHostFragment).setGraph(R.navigation.app_nav_graph)
    }

    override fun render(viewState: ContainerViewState) {
        // Do nothing
    }
}