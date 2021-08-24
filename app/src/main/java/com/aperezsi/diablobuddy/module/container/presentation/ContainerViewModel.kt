package com.aperezsi.diablobuddy.module.container.presentation

import androidx.lifecycle.viewModelScope
import com.aperezsi.core.framework.base.BaseViewModel
import com.aperezsi.core.utilities.time.TimeValidator
import com.aperezsi.diablobuddy.module.container.data.AuthRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val navigate = MutableStateFlow(false)

    fun initialize() {
        if (sessionPreferences.tokenExpiresOnLessThan(TimeValidator.MINUTES_5)) {
            viewModelScope.launch {
                authRepository.authenticate().collect {
                    navigate.value = it
                }
            }
        }
    }
}