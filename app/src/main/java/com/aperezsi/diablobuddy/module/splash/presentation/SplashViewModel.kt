package com.aperezsi.diablobuddy.module.splash.presentation

import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.core.utilities.coroutines.onSuccessCompletion
import com.aperezsi.core.utilities.time.TimeValidator
import com.aperezsi.diablobuddy.module.container.presentation.ContainerActivity
import com.aperezsi.diablobuddy.module.splash.domain.DoLoginUseCase
import com.aperezsi.diablobuddy.module.splash.domain.GetCurrentSeasonUseCase
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashEvent
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashViewState
import com.aperezsi.diablobuddy.shared.navigator.Navigator
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider,
    private val sessionPreferences: SessionPreferences,
    private val navigator: Navigator,
    private val doLoginUseCase: DoLoginUseCase,
    private val getCurrentSeasonUseCase: GetCurrentSeasonUseCase
): BaseViewModel<SplashViewState, SplashEvent>(logger, dispatcherProvider) {

    override fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.Initialize -> setupSessionConfig()
        }
    }

    private fun setupSessionConfig() {
        launchWithHandler {
            if (sessionPreferences.tokenExpiresOnLessThan(TimeValidator.MINUTES_5)) {
                doLoginUseCase().onSuccessCompletion { getCurrentSeasonUseCase() }.collect { navigator.navigate(ContainerActivity::class.java, true) }
            } else {
                getCurrentSeasonUseCase().collect { navigator.navigate(ContainerActivity::class.java, true) }
            }
        }
    }
}