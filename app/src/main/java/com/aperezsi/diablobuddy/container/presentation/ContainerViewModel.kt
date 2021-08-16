package com.aperezsi.diablobuddy.container.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aperezsi.core.date.TimeValidator
import com.aperezsi.diablobuddy.container.data.AuthRepository
import com.aperezsi.diablobuddy.shared.storage.SessionPreferences
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContainerViewModel @Inject constructor(
    private val sessionPreferences: SessionPreferences,
    private val authRepository: AuthRepository
) : ViewModel() {

    fun initialize() {
        if (sessionPreferences.tokenExpiresOnLessThan(TimeValidator.MINUTES_5)) {
            viewModelScope.launch {
                authRepository.authenticate()
            }
        }
    }
}