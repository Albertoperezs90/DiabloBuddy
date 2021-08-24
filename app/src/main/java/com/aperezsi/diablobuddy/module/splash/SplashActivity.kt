package com.aperezsi.diablobuddy.module.splash

import com.aperezsi.core.framework.base.BaseActivity
import com.aperezsi.core.framework.provideViewModel
import com.aperezsi.diablobuddy.databinding.ActivitySplashBinding
import com.aperezsi.diablobuddy.module.di.inject

class SplashActivity: BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by lazy { provideViewModel(SplashViewModel::class) }

    override fun inflate(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun setUpView() {
        inject()
    }

    override fun initialize() {
        viewModel.initialize()
    }
}