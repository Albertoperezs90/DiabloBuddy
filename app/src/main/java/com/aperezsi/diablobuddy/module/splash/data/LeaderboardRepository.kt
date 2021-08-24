package com.aperezsi.diablobuddy.module.splash.data

import com.aperezsi.feature_menu.data.LeaderboardApi
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(private val leaderboardApi: LeaderboardApi) {

    suspend fun getSeasonIndex() = leaderboardApi.getSeasonIndex().currentSeason

}