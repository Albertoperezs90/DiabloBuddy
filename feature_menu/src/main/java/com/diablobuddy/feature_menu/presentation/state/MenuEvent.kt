package com.diablobuddy.feature_menu.presentation.state

import com.diablobuddy.core.state.Event

sealed class MenuEvent: Event {
    object Initialize: MenuEvent()
}