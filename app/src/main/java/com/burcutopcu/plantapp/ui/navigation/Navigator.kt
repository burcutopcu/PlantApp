package com.burcutopcu.plantapp.ui.navigation

interface Navigator {
    fun popBackStack()
    fun navigateTo(route: String)
    fun navigateTo(destination: AppDestination)
    fun navigateWithPopUp(route: String, popUpRoute: String, inclusive: Boolean = false)
    fun navigateWithPopUp(
        destination: AppDestination,
        popUpDestination: AppDestination,
        inclusive: Boolean = false
    )
    fun navigateAndClearStack(destination: AppDestination)
}