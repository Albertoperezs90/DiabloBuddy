package com.diablobuddy.feature_skills.character_select

import com.diablobuddy.core.framework.base.BaseViewModel
import com.diablobuddy.core.interfaces.logger.Logger
import com.diablobuddy.core.utilities.coroutines.DispatcherProvider
import com.diablobuddy.feature_skills.character_select.state.CharacterSkillsEvent
import com.diablobuddy.feature_skills.character_select.state.CharacterSkillsViewState
import javax.inject.Inject

class CharacterSkillsViewModel @Inject constructor(
    logger: Logger,
    dispatcherProvider: DispatcherProvider
): BaseViewModel<CharacterSkillsViewState, CharacterSkillsEvent>(logger, dispatcherProvider) {

    override fun onEvent(event: CharacterSkillsEvent) {
        // Do nothing
    }
}