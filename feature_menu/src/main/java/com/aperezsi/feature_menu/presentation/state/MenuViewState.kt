package com.aperezsi.feature_menu.presentation.state

import com.aperezsi.core.state.ViewState
import com.aperezsi.core.views.CircularMenuConfig

sealed class MenuViewState: ViewState {
    class DrawSeason(val seasonIndex: Int): MenuViewState()
    class DrawMenu(val menuConfig: CircularMenuConfig): MenuViewState()
}