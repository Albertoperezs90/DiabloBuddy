package com.aperezsi.diablobuddy.module.container.presentation

import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.core.utilities.coroutines.DispatcherProvider
import com.aperezsi.diablobuddy.module.container.presentation.state.ContainerEvent
import com.aperezsi.diablobuddy.module.container.presentation.state.ContainerViewState
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider
): BaseViewModel<ContainerViewState, ContainerEvent>(logger, dispatcherProvider) {

    override fun onEvent(event: ContainerEvent) {
        // Do nothing
    }
}