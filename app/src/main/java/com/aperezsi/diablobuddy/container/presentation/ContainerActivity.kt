package com.aperezsi.diablobuddy.container.presentation

import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.diablobuddy.R
import com.aperezsi.diablobuddy.container.inject
import com.aperezsi.diablobuddy.databinding.ActivityContainerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContainerActivity : BaseActivity<ActivityContainerBinding, ContainerViewModel>() {

    override val viewModel: ContainerViewModel by lazy { provideViewModel(ContainerViewModel::class) }

    override fun inflate(): ActivityContainerBinding = ActivityContainerBinding.inflate(layoutInflater)

    override fun setUpView() {
        inject()

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.navigate.collect {
                if (it) findNavController(R.id.navHostFragment).setGraph(R.navigation.app_nav_graph)
            }
        }
    }

    override fun initialize() {
        viewModel.initialize()
    }
}