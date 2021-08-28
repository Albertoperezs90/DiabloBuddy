package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.diablobuddy.module.splash.data.repository.LeaderboardRepository
import com.aperezsi.diablobuddy.shared.storage.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrentSeasonUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val leaderboardRepository: LeaderboardRepository,
    private val appPreferences: AppPreferences
) {

    suspend operator fun invoke(): Flow<Int> =
        leaderboardRepository.getSeasonIndex()
            .flowOn(dispatcherProvider.io)
            .map {
                appPreferences.setSeasonIndex(it)
                it
            }
}