package com.diablobuddy.app.module.container.presentation

import com.diablobuddy.core.framework.base.BaseViewModel
import com.diablobuddy.core.interfaces.logger.Logger
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import com.diablobuddy.app.module.container.presentation.state.ContainerEvent
import com.diablobuddy.app.module.container.presentation.state.ContainerViewState
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider
): BaseViewModel<ContainerViewState, ContainerEvent>(logger, dispatcherProvider) {

    override fun onEvent(event: ContainerEvent) {
        // Do nothing
    }
}