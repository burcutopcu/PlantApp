package com.burcutopcu.plantapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import com.burcutopcu.plantapp.R
import com.burcutopcu.plantapp.ui.navigation.AppDestination
import com.burcutopcu.plantapp.ui.navigation.Navigator
import com.burcutopcu.plantapp.ui.theme.BottomNavBarGray
import com.burcutopcu.plantapp.ui.theme.ButtonGreen

@Composable
fun BottomNavigationBar(
    currentDestination: NavDestination?,
    navigator: Navigator
) {
    Column {
        Divider(
            modifier = Modifier.height(0.4.dp),
            color = Color.Gray.copy(alpha = 0.8f)
        )
        Box {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                // Home
                val isHomeSelected = currentDestination?.route == AppDestination.Home.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_home
                            ),
                            contentDescription = "Home",
                            modifier = Modifier.size(24.dp),
                            tint = if (isHomeSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    label = {
                        Text(
                            text = "Home",
                            fontSize = 10.sp,
                            color = if (isHomeSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    selected = isHomeSelected,
                    onClick = {
                        if (!isHomeSelected) {
                            navigator.navigateTo(AppDestination.Home.route)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )

                // Diagnose
                val isDiagnoseSelected = currentDestination?.route == AppDestination.Diagnose.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_diagnose
                            ),
                            contentDescription = "Diagnose",
                            modifier = Modifier.size(24.dp),
                            tint = if (isDiagnoseSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    label = {
                        Text(
                            text = "Diagnose",
                            fontSize = 10.sp,
                            color = if (isDiagnoseSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    selected = isDiagnoseSelected,
                    onClick = {
                        if (!isDiagnoseSelected) {
                            navigator.navigateTo(AppDestination.Diagnose.route)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                // My Garden
                val isGardenSelected = currentDestination?.route == AppDestination.MyGarden.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_my_garden
                            ),
                            contentDescription = "My Garden",
                            modifier = Modifier.size(24.dp),
                            tint = if (isGardenSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    label = {
                        Text(
                            text = "My Garden",
                            fontSize = 10.sp,
                            color = if (isGardenSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    selected = isGardenSelected,
                    onClick = {
                        if (!isGardenSelected) {
                            navigator.navigateTo(AppDestination.MyGarden.route)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )

                // Profile
                val isProfileSelected = currentDestination?.route == AppDestination.Profile.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_profile
                            ),
                            contentDescription = "Profile",
                            modifier = Modifier.size(24.dp),
                            tint = if (isProfileSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    label = {
                        Text(
                            text = "Profile",
                            fontSize = 10.sp,
                            color = if (isProfileSelected) ButtonGreen else BottomNavBarGray
                        )
                    },
                    selected = isProfileSelected,
                    onClick = {
                        if (!isProfileSelected) {
                            navigator.navigateTo(AppDestination.Profile.route)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
            }

            FloatingActionButton(
                onClick = {
                    navigator.navigateTo(AppDestination.Scan.route)
                },
                containerColor = ButtonGreen,
                shape = CircleShape,
                contentColor = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-40).dp)
                    .size(66.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_scan),
                    contentDescription = "Scan",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}