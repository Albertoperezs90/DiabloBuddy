package com.aperezsi.diablobuddy.module.splash.presentation

import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.time.TimeValidator
import com.aperezsi.core_testing.base.ViewModelTest
import com.aperezsi.core_testing.utilities.thenMockFlowResult
import com.aperezsi.core_testing.utilities.thenMockThrowable
import com.aperezsi.diablobuddy.module.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.module.splash.domain.DoLoginUseCase
import com.aperezsi.diablobuddy.module.splash.domain.GetCurrentSeasonUseCase
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashEvent
import com.aperezsi.diablobuddy.shared.navigator.Navigator
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SplashViewModelTest: ViewModelTest() {

    private val logger: Logger = mock()
    private val dispatcherProvider: DispatcherProvider = mock()
    private val sessionPreferences: SessionPreferences = mock()
    private val navigator: Navigator = mock()
    private val doLoginUseCase: DoLoginUseCase = mock()
    private val getCurrentSeasonUseCase: GetCurrentSeasonUseCase = mock()

    private val splashViewModel = SplashViewModel(logger, dispatcherProvider, sessionPreferences, navigator, doLoginUseCase, getCurrentSeasonUseCase)

    @Before
    fun setUp() {
        configureDispatcher(dispatcherProvider)
    }

    @Test
    fun `on Initialize Event should launch login and season use case and navigate if both success`() = runBlocking {
        whenever(doLoginUseCase.invoke()).thenMockFlowResult(Unit)
        whenever(getCurrentSeasonUseCase.invoke()).thenMockFlowResult(1)
        whenever(sessionPreferences.tokenExpiresOnLessThan(TimeValidator.MINUTES_5)).thenReturn(true)

        splashViewModel.onEvent(SplashEvent.Initialize)

        verify(sessionPreferences).tokenExpiresOnLessThan(TimeValidator.MINUTES_5)
        verify(doLoginUseCase).invoke()
        verify(getCurrentSeasonUseCase).invoke()
        verify(navigator).navigate(ContainerActivity::class.java, true)
    }

    @Test
    fun `On Initialize Event should launch only loginUseCase if fail`() = runBlocking {
        whenever(doLoginUseCase.invoke()).thenMockThrowable()
        whenever(sessionPreferences.tokenExpiresOnLessThan(TimeValidator.MINUTES_5)).thenReturn(true)

        splashViewModel.onEvent(SplashEvent.Initialize)

        verify(sessionPreferences).tokenExpiresOnLessThan(TimeValidator.MINUTES_5)
        verify(doLoginUseCase).invoke()
        verify(getCurrentSeasonUseCase, never()).invoke()
        verify(navigator, never()).navigate(any(), any())
    }

    @Test
    fun `On Initialize Event shouldn't navigate if getSeasonUseCase fail`() = runBlocking {
        whenever(doLoginUseCase.invoke()).thenMockFlowResult(Unit)
        whenever(getCurrentSeasonUseCase.invoke()).thenMockThrowable()
        whenever(sessionPreferences.tokenExpiresOnLessThan(TimeValidator.MINUTES_5)).thenReturn(true)

        splashViewModel.onEvent(SplashEvent.Initialize)

        verify(sessionPreferences).tokenExpiresOnLessThan(TimeValidator.MINUTES_5)
        verify(doLoginUseCase).invoke()
        verify(getCurrentSeasonUseCase).invoke()
        verify(navigator, never()).navigate(any(), any())
    }
}