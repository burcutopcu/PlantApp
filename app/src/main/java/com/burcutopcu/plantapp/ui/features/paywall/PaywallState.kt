package com.burcutopcu.plantapp.ui.features.paywall

data class PaywallState(
    val uiState: UiState = UiState.Loading,
)

enum class UiState {
    Loading,
    Success,
    Failure
}