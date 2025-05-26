package com.burcutopcu.plantapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
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
import com.burcutopcu.plantapp.ui.components.BottomNavigationBar
import com.burcutopcu.plantapp.ui.features.diagnose.DiagnoseScreen
import com.burcutopcu.plantapp.ui.features.home.HomeScreen
import com.burcutopcu.plantapp.ui.features.mygarden.MyGardenScreen
import com.burcutopcu.plantapp.ui.features.onboarding.GetStartedScreen
import com.burcutopcu.plantapp.ui.features.onboarding.OnboardingFinishScreen
import com.burcutopcu.plantapp.ui.features.onboarding.OnboardingStartScreen
import com.burcutopcu.plantapp.ui.features.onboarding.OnboardingViewModel
import com.burcutopcu.plantapp.ui.features.paywall.PaywallScreen
import com.burcutopcu.plantapp.ui.features.profile.ProfileScreen
import com.burcutopcu.plantapp.ui.features.scan.ScanScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavHost() {
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()

    val navController = rememberNavController()
    val navigator = remember { NavControllerNavigator(navController) }
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = if (onboardingViewModel.isOnboardingCompleted()) {
        AppDestination.Home.route
    } else {
        AppDestination.GetStarted.route
    }

    val showBottomBar = currentDestination?.route in NavigationItem.items.map { it.route }

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(currentDestination, navigator)
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
                composable(AppDestination.GetStarted.route) {
                    GetStartedScreen(navigator = navigator)
                }

                composable(AppDestination.OnboardingStart.route) {
                    OnboardingStartScreen(navigator = navigator)
                }

                composable(AppDestination.OnboardingFinish.route) {
                    OnboardingFinishScreen(navigator = navigator)
                }

                composable(AppDestination.Paywall.route) {
                    PaywallScreen(navigator = navigator)
                }

                composable(AppDestination.Home.route) {
                    HomeScreen(navigator = navigator)
                }

                composable(AppDestination.Diagnose.route) {
                    DiagnoseScreen()
                }

                composable(AppDestination.Scan.route) {
                    ScanScreen()
                }

                composable(AppDestination.MyGarden.route) {
                    MyGardenScreen()
                }

                composable(AppDestination.Profile.route) {
                    ProfileScreen()
                }
            }
        }
    }
}