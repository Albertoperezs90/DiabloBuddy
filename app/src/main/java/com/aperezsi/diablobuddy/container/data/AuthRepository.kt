package com.aperezsi.diablobuddy.container.data

import com.aperezsi.core.date.TimeProvider
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    private val timeProvider: TimeProvider,
    private val authApi: AuthApi
) {

    suspend fun authenticate() {
        val response = authApi.getToken()
        sessionPreferences.storeAccessToken(response.accessToken)
        sessionPreferences.storeAccessTokenExpires(response.expiresIn, timeProvider.getSeconds(timeProvider.getCurrentTime()))
    }
}