package com.aperezsi.feature_menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aperezsi.feature_menu.data.LeaderboardRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val leaderboardRepository: LeaderboardRepository) : ViewModel() {

    fun initialize() {
        viewModelScope.launch {
            leaderboardRepository.getSeasonIndex()
        }
    }
}