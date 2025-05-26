package com.burcutopcu.plantapp.ui.features.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.burcutopcu.plantapp.R
import com.burcutopcu.plantapp.ui.navigation.AppDestination
import com.burcutopcu.plantapp.ui.navigation.Navigator
import com.burcutopcu.plantapp.ui.theme.ButtonGreen
import com.burcutopcu.plantapp.ui.theme.MainTextColor
import com.burcutopcu.plantapp.ui.theme.TextGray

@Composable
fun GetStartedScreen(
    navigator: Navigator
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_full_screen_get_started),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.welcome_text))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.app_name))
                    }
                },
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                color = MainTextColor,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.get_started_description),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                color = MainTextColor,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_get_started),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Button(
                onClick = {
                    navigator.navigateTo(AppDestination.OnboardingStart)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonGreen
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.get_started_button),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = stringResource(R.string.get_started_agreement),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 50.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = TextGray,
                lineHeight = 16.sp
            )
        }
    }
}