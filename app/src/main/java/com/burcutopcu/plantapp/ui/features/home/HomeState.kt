package com.burcutopcu.plantapp.ui.features.home

data class HomeState(
    val uiState: UiState = UiState.Loading,
)

enum class UiState {
    Loading,
    Success,
    Failure
}