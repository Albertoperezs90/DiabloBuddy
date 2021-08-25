package com.aperezsi.diablobuddy.module.splash.presentation.state

import com.aperezsi.core.state.Event

sealed class SplashEvent: Event {
    object Initialize: SplashEvent()
}