package com.aperezsi.diablobuddy.module.splash.domain

import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core_testing.utilities.thenMockFlowResult
import com.aperezsi.diablobuddy.module.splash.data.repository.LeaderboardRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCurrentSeasonUseCaseTest {

    private val dispatcherProvider: DispatcherProvider = mock()
    private val leaderboardRepository: LeaderboardRepository = mock()
    private val sessionPreferences: SessionPreferences = mock()

    private val getCurrentSeasonUseCase = GetCurrentSeasonUseCase(dispatcherProvider, leaderboardRepository, sessionPreferences)

    @Before
    fun setUp() {
        whenever(dispatcherProvider.io).thenReturn(Dispatchers.Unconfined)
    }

    @Test
    fun `should call getSeasonIndex then store result into preferences and return it`() = runBlocking {
        whenever(leaderboardRepository.getSeasonIndex()).thenMockFlowResult(24)

        var result: Int? = null
        getCurrentSeasonUseCase.invoke().collect { result = it }

        verify(leaderboardRepository).getSeasonIndex()
        verify(sessionPreferences).setSeasonIndex(24)
        assertEquals(24, result)
    }
}