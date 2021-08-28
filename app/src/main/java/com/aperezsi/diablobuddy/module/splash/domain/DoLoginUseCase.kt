package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.time.TimeProvider
import com.aperezsi.diablobuddy.module.splash.data.repository.AuthRepository
import com.aperezsi.diablobuddy.shared.storage.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val authRepository: AuthRepository,
    private val appPreferences: AppPreferences,
    private val timeProvider: TimeProvider
) {

    companion object {
        private const val MILISECONDS_TO_SECONDS = 1000
    }

    suspend operator fun invoke(): Flow<Unit> =
        authRepository.authenticate()
            .flowOn(dispatcherProvider.io)
            .map { loginData ->
                appPreferences.storeAccessToken(loginData.accessToken)
                appPreferences.storeAccessTokenExpires(loginData.expiresIn, timeProvider.getCurrentTime() / MILISECONDS_TO_SECONDS)
            }
}