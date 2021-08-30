package com.diablobuddy.app.module.container.presentation

import androidx.navigation.findNavController
import com.diablobuddy.app.R
import com.diablobuddy.app.databinding.ActivityContainerBinding
import com.diablobuddy.app.module.container.presentation.state.ContainerViewState
import com.diablobuddy.app.module.di.inject
import com.diablobuddy.core.framework.base.BaseActivity
import com.diablobuddy.core.framework.provideViewModel

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