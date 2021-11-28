package com.diablobuddy.feature_menu.presentation

import com.diablobuddy.app.shared.navigator.Navigator
import com.diablobuddy.app.shared.storage.AppPreferences
import com.diablobuddy.core.framework.base.BaseViewModel
import com.diablobuddy.core.interfaces.logger.Logger
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import com.diablobuddy.feature_menu.presentation.state.MenuEvent
import com.diablobuddy.feature_menu.presentation.state.MenuViewState
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider,
    private val appPreferences: AppPreferences,
    private val menuBuilder: MenuBuilder,
    private val navigator: Navigator
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
            .add(MenuItem.Skills(::onClickSkills))
            .add(MenuItem.Gear)
            .add(MenuItem.Crafting)
            .add(MenuItem.Follower)
            .build()
        updateViewState(MenuViewState.DrawMenu(circularMenuConfig))
    }

    private fun onClickSkills() {
        navigator.navigateDeeplink("feature://skills")
    }
}