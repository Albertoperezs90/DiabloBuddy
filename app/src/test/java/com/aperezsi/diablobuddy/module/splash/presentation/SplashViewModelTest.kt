package com.aperezsi.diablobuddy.module.splash.presentation

import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.time.TimeValidator
import com.aperezsi.core_testing.base.ViewModelTest
import com.aperezsi.core_testing.utilities.thenMockFlow
import com.aperezsi.diablobuddy.module.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.module.splash.domain.DoLoginUseCase
import com.aperezsi.diablobuddy.module.splash.domain.GetCurrentSeasonUseCase
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashEvent
import com.aperezsi.diablobuddy.shared.navigator.Navigator
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
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
        whenever(doLoginUseCase.invoke()).thenMockFlow(Unit)
        whenever(getCurrentSeasonUseCase.invoke()).thenMockFlow(1)
        whenever(sessionPreferences.tokenExpiresOnLessThan(TimeValidator.MINUTES_5)).thenReturn(true)

        splashViewModel.onEvent(SplashEvent.Initialize)

        verify(sessionPreferences).tokenExpiresOnLessThan(TimeValidator.MINUTES_5)
        verify(doLoginUseCase).invoke()
        verify(getCurrentSeasonUseCase).invoke()
        verify(navigator).navigate(ContainerActivity::class.java, true)
    }
}