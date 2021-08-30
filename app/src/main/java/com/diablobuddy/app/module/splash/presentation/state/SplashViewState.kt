package com.diablobuddy.app.module.splash.presentation.state

import com.diablobuddy.core.state.ViewState

sealed class SplashViewState: ViewState {
    object Navigate: SplashViewState()
}