package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.usecase.UseCase
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.time.TimeProvider
import com.aperezsi.diablobuddy.module.splash.data.repository.AuthRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionPreferences: SessionPreferences,
    private val timeProvider: TimeProvider
): UseCase<Unit, Unit>() {

    override suspend fun invoke(params: Unit?) = withContext(DispatcherProvider.io()) {
        val response = authRepository.authenticate()
        sessionPreferences.storeAccessToken(response.accessToken)
        sessionPreferences.storeAccessTokenExpires(response.expiresIn, timeProvider.getSeconds(timeProvider.getCurrentTime()))
    }
}