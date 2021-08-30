package com.diablobuddy.feature_menu.presentation.state

import com.diablobuddy.core.state.ViewState
import com.diablobuddy.core.views.CircularMenuConfig

sealed class MenuViewState: ViewState {
    class DrawSeason(val seasonIndex: Int): MenuViewState()
    class DrawMenu(val menuConfig: CircularMenuConfig): MenuViewState()
}