package com.diablobuddy.app.module.splash.domain

import com.diablobuddy.app.module.splash.data.repository.LeaderboardRepository
import com.diablobuddy.app.shared.storage.AppPreferences
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
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
            .map { seasonIndex ->
                appPreferences.setSeasonIndex(seasonIndex)
                seasonIndex
            }
}