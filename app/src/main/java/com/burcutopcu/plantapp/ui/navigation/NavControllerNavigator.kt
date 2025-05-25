package com.burcutopcu.plantapp.ui.navigation

import androidx.navigation.NavController

class NavControllerNavigator(private val navController: NavController) : Navigator {
    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun navigateTo(destination: AppDestination) {
        navController.navigate(destination.route)
    }

    override fun navigateTo(route: String) {
        navController.navigate(route)
    }

    override fun navigateWithPopUp(
        destination: AppDestination,
        popUpDestination: AppDestination,
        inclusive: Boolean
    ) {
        navController.navigate(destination.route) {
            popUpTo(popUpDestination.route) {
                this.inclusive = inclusive
            }
        }
    }

    override fun navigateWithPopUp(route: String, popUpRoute: String, inclusive: Boolean) {
        navController.navigate(route) {
            popUpTo(popUpRoute) {
                this.inclusive = inclusive
            }
        }
    }

    override fun navigateAndClearStack(destination: AppDestination) {
        navController.navigate(destination.route) {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}