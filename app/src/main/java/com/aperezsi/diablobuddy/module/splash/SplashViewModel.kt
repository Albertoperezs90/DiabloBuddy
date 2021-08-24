package com.aperezsi.diablobuddy.module.splash

import androidx.lifecycle.viewModelScope
import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.diablobuddy.module.splash.domain.GetCurrentSeason
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val getCurrentSeason: GetCurrentSeason, private val sessionPreferences: SessionPreferences): BaseViewModel() {

    fun initialize() {
        viewModelScope.launch(Dispatchers.Main) {
            getCurrentSeason()
        }
    }
}