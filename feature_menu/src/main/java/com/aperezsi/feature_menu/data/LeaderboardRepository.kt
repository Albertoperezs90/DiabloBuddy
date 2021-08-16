package com.aperezsi.feature_menu.data

import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(private val sessionPreferences: SessionPreferences, private val leaderboardApi: LeaderboardApi) {

    suspend fun getSeasonIndex() {
        val response = leaderboardApi.getSeasonIndex(sessionPreferences.getAccessToken())
    }
}