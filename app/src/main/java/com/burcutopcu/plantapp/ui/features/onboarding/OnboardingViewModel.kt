package com.burcutopcu.plantapp.ui.features.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burcutopcu.plantapp.domain.onboarding.CheckOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val checkOnboardingUseCase: CheckOnboardingUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnboardingState>(OnboardingState.Idle)
    val uiState: StateFlow<OnboardingState> = _uiState.asStateFlow()

    init {
        checkOnboardingStatus()
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            try {
                _uiState.value = OnboardingState.Loading
                val isCompleted = checkOnboardingUseCase()
                _uiState.value = OnboardingState.Success(isCompleted)
            } catch (e: Exception) {
                _uiState.value = OnboardingState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun isOnboardingCompleted(): Boolean {
        return checkOnboardingUseCase()
    }
}