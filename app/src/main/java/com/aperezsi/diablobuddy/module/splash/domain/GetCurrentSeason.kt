package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.diablobuddy.module.splash.data.LeaderboardRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentSeason @Inject constructor(
    private val leaderboardRepository: LeaderboardRepository,
    private val sessionPreferences: SessionPreferences
) {

    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        val seasonIndex = leaderboardRepository.getSeasonIndex()
        sessionPreferences.setSeasonIndex(seasonIndex)
    }
}