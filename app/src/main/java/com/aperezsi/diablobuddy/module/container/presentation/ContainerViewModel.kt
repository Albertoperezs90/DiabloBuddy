package com.aperezsi.diablobuddy.module.container.presentation

import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.interfaces.logger.Logger
import com.aperezsi.diablobuddy.module.container.presentation.state.ContainerEvent
import com.aperezsi.diablobuddy.module.container.presentation.state.ContainerViewState
import javax.inject.Inject

class ContainerViewModel @Inject constructor(logger: Logger): BaseViewModel<ContainerViewState, ContainerEvent>(logger) {

    override fun onEvent(event: ContainerEvent) {
        // Do nothing
    }
}