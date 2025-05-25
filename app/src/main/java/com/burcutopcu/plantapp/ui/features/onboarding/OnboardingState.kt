package com.burcutopcu.plantapp.ui.features.onboarding

data class OnboardingState(
    val uiState: UiState = UiState.Loading,
)

enum class UiState {
    Loading,
    Success,
    Failure
}