package com.burcutopcu.plantapp.ui.features.paywall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burcutopcu.plantapp.domain.onboarding.CompleteOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaywallViewModel @Inject constructor(
    private val completeOnboardingUseCase: CompleteOnboardingUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<PaywallState>(PaywallState.Loading)
    val uiState: StateFlow<PaywallState> = _uiState.asStateFlow()

    fun completeOnboarding() {
        viewModelScope.launch {
            try {
                _uiState.value = PaywallState.Loading
                val result = completeOnboardingUseCase()

                if (result.isSuccess) {
                    _uiState.value = PaywallState.Success(isCompleted = true)
                } else {
                    val error = result.exceptionOrNull()?.message ?: "Failed to complete onboarding"
                    _uiState.value = PaywallState.Error(error)
                }
            } catch (e: Exception) {
                _uiState.value = PaywallState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}