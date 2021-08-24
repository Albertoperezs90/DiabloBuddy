package com.aperezsi.feature_menu.presentation

import androidx.lifecycle.viewModelScope
import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.views.CircularItemConfig
import com.aperezsi.core.views.CircularMenuConfig
import com.aperezsi.feature_menu.R
import com.aperezsi.feature_menu.domain.GetCurrentSeason
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val getCurrentSeason: GetCurrentSeason) : BaseViewModel() {

    val currentSeason = MutableStateFlow(0)
    val menuConfig: MutableStateFlow<CircularMenuConfig?> = MutableStateFlow(null)

    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentSeason().collect { currentSeason.value = it }
        }
        menuConfig.value = CircularMenuConfig(R.drawable.ic_central_icon, listOf(
            CircularItemConfig(R.drawable.ic_skills, R.string.skills),
            CircularItemConfig(R.drawable.ic_gear, R.string.gear),
            CircularItemConfig(R.drawable.ic_crafting, R.string.crafting),
            CircularItemConfig(R.drawable.ic_follower, R.string.follower)
        ))
    }
}