package com.diablobuddy.app.module.splash.presentation

import android.content.Intent
import com.diablobuddy.app.databinding.ActivitySplashBinding
import com.diablobuddy.app.module.container.presentation.ContainerActivity
import com.diablobuddy.app.module.di.inject
import com.diablobuddy.app.module.splash.presentation.state.SplashEvent
import com.diablobuddy.app.module.splash.presentation.state.SplashViewState
import com.diablobuddy.core.framework.base.BaseActivity
import com.diablobuddy.core.framework.provideViewModel

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