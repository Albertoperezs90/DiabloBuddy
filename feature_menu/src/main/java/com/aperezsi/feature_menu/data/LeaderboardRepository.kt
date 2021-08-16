package com.aperezsi.feature_menu.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(private val leaderboardApi: LeaderboardApi) {

    fun getSeasonIndex() = flow {
        val response = leaderboardApi.getSeasonIndex()
        emit(response.currentSeason)
    }.flowOn(Dispatchers.IO)
}