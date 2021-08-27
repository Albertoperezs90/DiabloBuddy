package com.aperezsi.diablobuddy.module.splash.data.repository

import com.aperezsi.diablobuddy.module.splash.data.api.AuthApi
import com.aperezsi.diablobuddy.module.splash.data.model.OauthResponse
import com.aperezsi.diablobuddy.module.splash.domain.model.LoginData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRepositoryTest {

    private val authApi: AuthApi = mock()

    private val authRepository = AuthRepository(authApi)

    @Test
    fun `authenticate should return login data if sucess`() = runBlocking {
        val oauthRepository = OauthResponse("accessToken", "tokenType", 5, "sub")
        whenever(authApi.getToken(any())).thenReturn(oauthRepository)

        var result: LoginData? = null
        authRepository.authenticate().catch { }.collect { result = it }

        verify(authApi).getToken("client_credentials")
        assertEquals(oauthRepository.toDomain(), result)
    }

    @Test
    fun `authenticate should return null if failed`() = runBlocking {
        whenever(authApi.getToken(any())).thenAnswer { throw Exception() }

        var result: LoginData? = null
        authRepository.authenticate().catch { }.collect { result = it }

        verify(authApi).getToken("client_credentials")
        assertNull(result)
    }
}