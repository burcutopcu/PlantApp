package com.burcutopcu.plantapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.burcutopcu.plantapp.ui.features.home.HomeScreen
import com.burcutopcu.plantapp.ui.features.onboarding.OnboardingScreen
import com.burcutopcu.plantapp.ui.features.onboarding.OnboardingViewModel
import com.burcutopcu.plantapp.ui.features.paywall.PaywallScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val navigator = remember { NavControllerNavigator(navController) }
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val startDestination = if (onboardingViewModel.isOnboardingCompleted()) {
        AppDestination.Home.route
    } else {
        AppDestination.Onboarding.route
    }

    val showBottomBar = currentDestination?.route in NavigationItem.items.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {

            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(AppDestination.Onboarding.route) {
                    OnboardingScreen(navigator = navigator)
                }

                composable(AppDestination.Paywall.route) {
                    PaywallScreen(navigator = navigator)
                }

                composable(AppDestination.Home.route) {
                    HomeScreen(navigator = navigator)
                }

                composable(AppDestination.Diagnose.route) {
                    // DiagnoseScreen(navigator = navigator)
                }

                composable(AppDestination.Scan.route) {
                    // ScanScreen(navigator = navigator)
                }

                composable(AppDestination.MyGarden.route) {
                    //  MyGardenScreen(navigator = navigator)
                }

                composable(AppDestination.Profile.route) {
                    // ProfileScreen(navigator = navigator)
                }
            }
        }
    }
}