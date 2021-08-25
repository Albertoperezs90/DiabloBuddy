package com.aperezsi.diablobuddy.module.splash.data.repository

import com.aperezsi.diablobuddy.module.splash.data.api.AuthApi
import com.aperezsi.diablobuddy.module.splash.domain.model.LoginData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi) {

    suspend fun authenticate(): Flow<LoginData> = flow {
        emit(authApi.getToken().toDomain())
    }
}