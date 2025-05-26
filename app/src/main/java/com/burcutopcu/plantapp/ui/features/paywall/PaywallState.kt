package com.burcutopcu.plantapp.ui.features.paywall

sealed class PaywallState {
    data object Loading : PaywallState()
    data class Success(val isCompleted: Boolean) : PaywallState()
    data class Error(val message: String) : PaywallState()
}