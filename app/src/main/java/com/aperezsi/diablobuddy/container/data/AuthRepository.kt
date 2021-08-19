package com.aperezsi.diablobuddy.container.data

import com.aperezsi.core.utilities.time.TimeProvider
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    private val timeProvider: TimeProvider,
    private val authApi: AuthApi
) {

    suspend fun authenticate(): Flow<Boolean> = flow {
        val response = authApi.getToken()
        sessionPreferences.storeAccessToken(response.accessToken)
        sessionPreferences.storeAccessTokenExpires(response.expiresIn, timeProvider.getSeconds(timeProvider.getCurrentTime()))
        emit(true)
    }
}