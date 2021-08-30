package com.diablobuddy.app.module.splash.presentation.state

import com.diablobuddy.core.state.Event

sealed class SplashEvent: Event {
    object Initialize: SplashEvent()
}