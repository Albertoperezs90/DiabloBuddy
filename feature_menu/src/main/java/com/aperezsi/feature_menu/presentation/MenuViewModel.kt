package com.aperezsi.feature_menu.presentation

import androidx.lifecycle.viewModelScope
import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.feature_menu.domain.GetCurrentSeason
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val getCurrentSeason: GetCurrentSeason) : BaseViewModel() {

    val currentSeason = MutableStateFlow(0)
    val menuItems = MutableStateFlow(emptyList<String>())


    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentSeason().collect { currentSeason.value = it }
        }
        menuItems.value = listOf("1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3")
    }
}