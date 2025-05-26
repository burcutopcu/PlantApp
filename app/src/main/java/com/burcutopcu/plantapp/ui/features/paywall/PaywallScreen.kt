package com.burcutopcu.plantapp.ui.features.paywall

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.burcutopcu.plantapp.R
import com.burcutopcu.plantapp.ui.navigation.AppDestination
import com.burcutopcu.plantapp.ui.navigation.Navigator

@Composable
fun PaywallScreen(
    navigator: Navigator,
    viewModel: PaywallViewModel = hiltViewModel()
) {

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
                        navigator.navigateAndClearStack(AppDestination.Home)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
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
                text = "PlantApp Premium",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Access All Features",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            val cardWidth = (screenWidth - 40.dp) / 2.5f // 40dp = horizontal padding

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    FeatureCard(
                        title = "Unlimited",
                        subtitle = "Plant Identify",
                        iconPlaceholder = R.drawable.ic_identify_unlimited,
                        modifier = Modifier.width(cardWidth)
                    )
                }

                item {
                    FeatureCard(
                        title = "Faster",
                        subtitle = "Process",
                        iconPlaceholder = R.drawable.ic_identify_faster,
                        modifier = Modifier.width(cardWidth)
                    )
                }

                item {
                    FeatureCard(
                        title = "Better",
                        subtitle = "Results",
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
                    title = "1 Month",
                    price = "$2.99/month, auto renewable",
                    isSelected = false,
                    onClick = { /* Handle selection */ }
                )

                Box {
                    PricingOption(
                        title = "1 Year",
                        price = "First 3 days free, then $529.99/year",
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
                            text = "Save 50%",
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
                    navigator.navigateAndClearStack(AppDestination.Home)
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
                    text = "Try free for 3 days",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "After the 3-day free trial period you'll be charged $274.99 per year unless you cancel before the trial expires. Yearly Subscription is Auto-Renewable",
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
                BottomLink(text = "Terms") { /* Handle Terms */ }
                Text(
                    text = " • ",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
                BottomLink(text = "Privacy") { /* Handle Privacy */ }
                Text(
                    text = " • ",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
                BottomLink(text = "Restore") { /* Handle Restore */ }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    subtitle: String,
    iconPlaceholder: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.3f)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        Color.White.copy(alpha = 0.2f),
                        RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconPlaceholder),
                    contentDescription = null,
                )
            }

            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun PricingOption(
    title: String,
    price: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                Color(0xFF28AF6E).copy(alpha = 0.2f)
            else
                Color.Black.copy(alpha = 0.3f)
        ),
        border = if (isSelected)
            BorderStroke(2.dp, Color(0xFF28AF6E))
        else
            BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF28AF6E),
                    unselectedColor = Color.White.copy(alpha = 0.7f)
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = price,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun BottomLink(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = Color.White.copy(alpha = 0.7f),
        fontSize = 11.sp,
        modifier = Modifier.clickable { onClick() }
    )
}