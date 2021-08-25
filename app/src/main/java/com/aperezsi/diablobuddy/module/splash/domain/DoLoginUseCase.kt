package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.time.TimeProvider
import com.aperezsi.diablobuddy.module.splash.data.repository.AuthRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionPreferences: SessionPreferences,
    private val timeProvider: TimeProvider
) {

    suspend fun invoke(): Flow<Unit> =
        authRepository.authenticate()
            .flowOn(DispatcherProvider.io())
            .map { loginData ->
                sessionPreferences.storeAccessToken(loginData.accessToken)
                sessionPreferences.storeAccessTokenExpires(loginData.expiresIn, timeProvider.getSeconds(timeProvider.getCurrentTime()))
            }
}