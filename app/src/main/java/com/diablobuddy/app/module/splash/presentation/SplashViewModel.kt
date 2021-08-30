package com.diablobuddy.app.module.splash.presentation

import com.diablobuddy.core.framework.base.BaseViewModel
import com.diablobuddy.core.interfaces.logger.Logger
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import com.diablobuddy.core.utilities.coroutines.onSuccessCompletion
import com.diablobuddy.app.module.container.presentation.ContainerActivity
import com.diablobuddy.app.module.splash.domain.DoLoginUseCase
import com.diablobuddy.app.module.splash.domain.GetCurrentSeasonUseCase
import com.diablobuddy.app.module.splash.presentation.state.SplashEvent
import com.diablobuddy.app.module.splash.presentation.state.SplashViewState
import com.diablobuddy.app.shared.navigator.Navigator
import com.diablobuddy.app.shared.storage.AppPreferences
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider,
    private val appPreferences: AppPreferences,
    private val navigator: Navigator,
    private val doLoginUseCase: DoLoginUseCase,
    private val getCurrentSeasonUseCase: GetCurrentSeasonUseCase
): BaseViewModel<SplashViewState, SplashEvent>(logger, dispatcherProvider) {

    companion object {
        private const val FIVE_MINUTES = 300
    }

    override fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.Initialize -> setupSessionConfig()
        }
    }

    private fun setupSessionConfig() {
        launchWithHandler {
            if (appPreferences.tokenExpiresOnLessThan(FIVE_MINUTES)) {
                doLoginUseCase().onSuccessCompletion { getCurrentSeasonUseCase() }.collect { navigator.navigate(ContainerActivity::class.java, true) }
            } else {
                getCurrentSeasonUseCase().collect { navigator.navigate(ContainerActivity::class.java, true) }
            }
        }
    }
}