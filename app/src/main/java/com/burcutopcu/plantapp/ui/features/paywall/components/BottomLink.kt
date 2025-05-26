package com.burcutopcu.plantapp.ui.features.paywall.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

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