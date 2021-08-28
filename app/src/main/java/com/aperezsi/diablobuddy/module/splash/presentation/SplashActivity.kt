package com.aperezsi.diablobuddy.module.splash.presentation

import android.content.Intent
import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.diablobuddy.databinding.ActivitySplashBinding
import com.aperezsi.diablobuddy.module.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.module.di.inject
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashEvent
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashViewState

class SplashActivity: BaseActivity<ActivitySplashBinding, SplashViewModel, SplashViewState>() {

    override val viewModel: SplashViewModel by lazy { provideViewModel(SplashViewModel::class) }

    override fun inflate(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun injectDependencies() {
        inject()
    }

    override fun initialize() {
        viewModel.onEvent(SplashEvent.Initialize)
    }

    override fun render(viewState: SplashViewState) {
        when (viewState) {
            SplashViewState.Navigate -> startActivity(Intent(this, ContainerActivity::class.java))
        }
    }
}