package com.burcutopcu.plantapp.ui.features.onboarding

sealed class OnboardingState {
    data object Idle : OnboardingState()
    data object Loading : OnboardingState()
    data class Success(val isCompleted: Boolean) : OnboardingState()
    data class Error(val message: String) : OnboardingState()
}