package com.burcutopcu.plantapp.ui.navigation

sealed class AppDestination(val route: String) {
    data object Onboarding : AppDestination("onboarding")
    data object Paywall : AppDestination("paywall")

    data object Home : AppDestination("home")
    data object Diagnose : AppDestination("diagnose")
    data object Scan : AppDestination("scan")
    data object MyGarden : AppDestination("my_garden")
    data object Profile : AppDestination("profile")
}