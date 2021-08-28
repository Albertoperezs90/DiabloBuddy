package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.diablobuddy.shared.storage.AppPreferences
import com.aperezsi.feature_menu.presentation.state.MenuEvent
import com.aperezsi.feature_menu.presentation.state.MenuViewState
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider,
    private val appPreferences: AppPreferences,
    private val menuBuilder: MenuBuilder
): BaseViewModel<MenuViewState, MenuEvent>(logger, dispatcherProvider) {

    override fun onEvent(event: MenuEvent) {
        when (event) {
            is MenuEvent.Initialize -> configureScreen()
        }
    }

    private fun configureScreen() {
        updateViewState(MenuViewState.DrawSeason(appPreferences.getSeasonIndex()))
        configureMenu()
    }

    private fun configureMenu() {
        val circularMenuConfig = menuBuilder
            .add(MenuItem.SKILLS)
            .add(MenuItem.GEAR)
            .add(MenuItem.CRAFTING)
            .add(MenuItem.FOLLOWER)
            .build()
        updateViewState(MenuViewState.DrawMenu(circularMenuConfig))
    }
}