package com.aperezsi.diablobuddy.module.splash.presentation

import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.utilities.coroutines.then
import com.aperezsi.diablobuddy.module.splash.domain.DoLoginUseCase
import com.aperezsi.diablobuddy.module.splash.domain.GetCurrentSeasonUseCase
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashEvent
import com.aperezsi.diablobuddy.module.splash.presentation.state.SplashViewState
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    logger: Logger,
    private val doLoginUseCase: DoLoginUseCase,
    private val getCurrentSeasonUseCase: GetCurrentSeasonUseCase
): BaseViewModel<SplashViewState, SplashEvent>(logger) {

    override fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.Initialize -> launchUseCases()
        }
    }

    private fun launchUseCases() {
        launchWithHandler {
            doLoginUseCase()
                .then { getCurrentSeasonUseCase() }
                .then { updateViewState(SplashViewState.Navigate) }
        }
    }
}