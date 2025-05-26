package com.burcutopcu.plantapp.ui.features.paywall

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.burcutopcu.plantapp.R
import com.burcutopcu.plantapp.ui.features.paywall.components.BottomLink
import com.burcutopcu.plantapp.ui.features.paywall.components.FeatureCard
import com.burcutopcu.plantapp.ui.features.paywall.components.PricingOption
import com.burcutopcu.plantapp.ui.navigation.AppDestination
import com.burcutopcu.plantapp.ui.navigation.Navigator

@Composable
fun PaywallScreen(
    navigator: Navigator,
    viewModel: PaywallViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.value) {
        if (uiState.value is PaywallState.Success && (uiState.value as PaywallState.Success).isCompleted) {
            navigator.navigateAndClearStack(AppDestination.Home)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF77A986),
                            Color(0xFF77A986),
                            Color(0xFF244128),
                            Color(0xFF0F1A11),
                            Color(0xFF000000)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_paywall_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color(0xFF4A7C59).copy(alpha = 0.3f),
                                Color(0xFF2D5233).copy(alpha = 0.7f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
                .padding(top = 14.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        Color.Black.copy(alpha = 0.3f),
                        CircleShape
                    )
                    .clickable {
                        viewModel.completeOnboarding()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.app_name))
                    }
                    append(stringResource(R.string.paywall_premium))
                },
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.paywall_access_all_features),
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val cardWidth = (screenWidth - 40.dp) / 2.5f

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    FeatureCard(
                        title = stringResource(R.string.paywall_unlimited),
                        subtitle = stringResource(R.string.paywall_plant_identity),
                        iconPlaceholder = R.drawable.ic_identify_unlimited,
                        modifier = Modifier.width(cardWidth)
                    )
                }

                item {
                    FeatureCard(
                        title = stringResource(R.string.paywall_plant_faster),
                        subtitle = stringResource(R.string.paywall_plant_process),
                        iconPlaceholder = R.drawable.ic_identify_faster,
                        modifier = Modifier.width(cardWidth)
                    )
                }

                item {
                    FeatureCard(
                        title = stringResource(R.string.paywall_plant_better),
                        subtitle = stringResource(R.string.paywall_plant_result),
                        iconPlaceholder = R.drawable.ic_identify_faster,
                        modifier = Modifier.width(cardWidth)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PricingOption(
                    title = stringResource(R.string.paywall_one_month),
                    price = stringResource(R.string.paywall_one_month_price),
                    isSelected = false,
                    onClick = { /* Handle selection */ }
                )

                Box {
                    PricingOption(
                        title = stringResource(R.string.paywall_one_year),
                        price = stringResource(R.string.paywall_one_year_price),
                        isSelected = true,
                        onClick = { /* Handle selection */ }
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .background(
                                Color(0xFF28AF6E),
                                RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 20.dp,
                                    bottomStart = 20.dp,
                                    bottomEnd = 0.dp
                                )
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.paywall_save_half),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.completeOnboarding()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF28AF6E)
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = true
            ) {
                Text(
                    text = stringResource(R.string.paywall_try_free_button),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.paywall_free_trial_description),
                fontSize = 9.sp,
                color = Color.White.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                lineHeight = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BottomLink(text = stringResource(R.string.paywall_terms)) { /* Handle Terms */ }
                Text(
                    text = stringResource(R.string.paywall_dot),
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
                BottomLink(text = stringResource(R.string.paywall_privacy)) { /* Handle Privacy */ }
                Text(
                    text = stringResource(R.string.paywall_dot),
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
                BottomLink(text = stringResource(R.string.paywall_restore)) { /* Handle Restore */ }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}