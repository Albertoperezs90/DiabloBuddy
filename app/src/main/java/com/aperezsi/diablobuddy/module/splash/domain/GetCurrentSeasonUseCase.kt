package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.usecase.UseCase
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.diablobuddy.module.splash.data.repository.LeaderboardRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentSeasonUseCase @Inject constructor(
    private val leaderboardRepository: LeaderboardRepository,
    private val sessionPreferences: SessionPreferences
): UseCase<Unit, Unit>() {

    override suspend operator fun invoke(params: Unit?) = withContext(DispatcherProvider.io()) {
        val seasonIndex = leaderboardRepository.getSeasonIndex()
        sessionPreferences.setSeasonIndex(seasonIndex)
    }
}