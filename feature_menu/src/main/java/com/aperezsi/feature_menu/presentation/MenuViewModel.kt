package com.aperezsi.feature_menu.presentation

import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.views.CircularItemConfig
import com.aperezsi.core.views.CircularMenuConfig
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import com.aperezsi.feature_menu.R
import com.aperezsi.feature_menu.presentation.state.MenuEvent
import com.aperezsi.feature_menu.presentation.state.MenuViewState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider,
    sessionPreferences: SessionPreferences
): BaseViewModel<MenuViewState, MenuEvent>(logger, dispatcherProvider) {

    val currentSeason = MutableStateFlow(sessionPreferences.getSeasonIndex())
    val menuConfig: MutableStateFlow<CircularMenuConfig?> = MutableStateFlow(null)

    fun initialize() {
        menuConfig.value = CircularMenuConfig(
            R.drawable.ic_central_icon, listOf(
                CircularItemConfig(R.drawable.ic_skills, R.string.skills),
                CircularItemConfig(R.drawable.ic_gear, R.string.gear),
                CircularItemConfig(R.drawable.ic_crafting, R.string.crafting),
                CircularItemConfig(R.drawable.ic_follower, R.string.follower)
            )
        )
    }

    override fun onEvent(event: MenuEvent) {
        // Do nothing
    }
}