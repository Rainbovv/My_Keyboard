package com.example.mykeyboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.mykeyboard.ui.main.KeyboardThemeViewModel
import kotlinx.coroutines.delay

@Composable
fun AdScreen(onTimeout: () -> Unit, viewModel: KeyboardThemeViewModel) {

    LaunchedEffect(Unit) {
        delay(5000)
        onTimeout()
    }

    Surface(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(viewModel.getBeautifulAdImageRes()),
            contentDescription = "Beautiful Ad!",
            modifier = Modifier.fillMaxSize()
        )
    }
}