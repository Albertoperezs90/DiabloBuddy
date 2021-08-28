package com.aperezsi.diablobuddy.module.splash.data.repository

import com.aperezsi.diablobuddy.module.splash.data.api.LeaderboardApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(private val leaderboardApi: LeaderboardApi) {

    suspend fun getSeasonIndex(): Flow<Int> = flow {
        emit(leaderboardApi.getSeasonIndex().currentSeason)
    }
}