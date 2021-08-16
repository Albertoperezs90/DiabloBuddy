package com.aperezsi.feature_menu.domain

import com.aperezsi.feature_menu.data.LeaderboardRepository
import javax.inject.Inject

class GetCurrentSeason @Inject constructor(private val leaderboardRepository: LeaderboardRepository) {

    operator fun invoke() = leaderboardRepository.getSeasonIndex()
}