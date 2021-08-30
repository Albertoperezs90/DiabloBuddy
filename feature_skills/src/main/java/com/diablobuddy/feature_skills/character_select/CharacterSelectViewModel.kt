package com.diablobuddy.feature_skills.character_select

import com.diablobuddy.core.framework.base.BaseViewModel
import com.diablobuddy.core.interfaces.logger.Logger
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import com.diablobuddy.feature_skills.character_select.state.CharacterSelectEvent
import com.diablobuddy.feature_skills.character_select.state.CharacterSelectViewState
import javax.inject.Inject

class CharacterSelectViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider
): BaseViewModel<CharacterSelectViewState, CharacterSelectEvent>(logger, dispatcherProvider) {

    override fun onEvent(event: CharacterSelectEvent) {
        // Do nothing
    }
}