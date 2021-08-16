package com.aperezsi.feature_menu.presentation

import androidx.lifecycle.asLiveData
import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.feature_menu.domain.GetCurrentSeason
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val getCurrentSeason: GetCurrentSeason): BaseViewModel() {

    val currentSeason = getCurrentSeason().asLiveData()
}