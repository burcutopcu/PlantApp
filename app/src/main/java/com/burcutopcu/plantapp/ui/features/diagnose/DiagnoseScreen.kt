package com.burcutopcu.plantapp.ui.features.diagnose

import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.burcutopcu.plantapp.R

@Composable
fun DiagnoseScreen() {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.coming_soon_screen, null)
        },
        modifier = Modifier.fillMaxSize()
    )
}

