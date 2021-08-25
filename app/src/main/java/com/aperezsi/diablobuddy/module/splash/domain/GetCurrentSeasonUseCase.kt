package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.diablobuddy.module.splash.data.repository.LeaderboardRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrentSeasonUseCase @Inject constructor(
    private val leaderboardRepository: LeaderboardRepository,
    private val sessionPreferences: SessionPreferences
) {

    suspend operator fun invoke(): Flow<Unit> =
        leaderboardRepository.getSeasonIndex()
            .flowOn(DispatcherProvider.io())
            .map {
                sessionPreferences.setSeasonIndex(it)
            }
}