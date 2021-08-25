package com.aperezsi.diablobuddy.module.splash.presentation.state

import com.aperezsi.core.state.ViewState

sealed class SplashViewState: ViewState {
    object Navigate: SplashViewState()
}