package com.aperezsi.diablobuddy.module.splash.data.repository

import com.aperezsi.diablobuddy.module.splash.data.api.LeaderboardApi
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(private val leaderboardApi: LeaderboardApi) {

    suspend fun getSeasonIndex() = leaderboardApi.getSeasonIndex().currentSeason
}