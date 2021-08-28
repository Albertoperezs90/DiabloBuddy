package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.time.TimeProvider
import com.aperezsi.core_testing.utilities.thenMockFlowResult
import com.aperezsi.diablobuddy.module.splash.data.repository.AuthRepository
import com.aperezsi.diablobuddy.module.splash.domain.model.LoginData
import com.aperezsi.diablobuddy.shared.storage.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DoLoginUseCaseTest {

    private val dispatcherProvider: DispatcherProvider = mock()
    private val authRepository: AuthRepository = mock()
    private val appPreferences: AppPreferences = mock()
    private val timeProvider: TimeProvider = mock()
    private val doLoginUseCase = DoLoginUseCase(dispatcherProvider, authRepository, appPreferences, timeProvider)

    @Before
    fun setUp() {
        whenever(dispatcherProvider.io).thenReturn(Dispatchers.Unconfined)
    }

    @Test
    fun `should call authRepository authenticate and store accesToken and accessTokenExpires on result`() = runBlocking {
        val loginData = LoginData("accessToken", 5)
        whenever(authRepository.authenticate()).thenMockFlowResult(loginData)
        whenever(timeProvider.getCurrentTime()).thenReturn(60000)

        val result = doLoginUseCase.invoke().collect()

        verify(appPreferences).storeAccessToken(loginData.accessToken)
        verify(appPreferences).storeAccessTokenExpires(loginData.expiresIn, 60)
        assertEquals(Unit, result)
    }
}