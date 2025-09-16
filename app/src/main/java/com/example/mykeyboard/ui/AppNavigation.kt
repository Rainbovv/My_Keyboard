package com.example.mykeyboard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mykeyboard.ui.main.KeyboardThemeViewModel
import com.example.mykeyboard.ui.main.MainScreen

@Composable
fun AppNavigation() {
    var showAd by remember { mutableStateOf(false) }

    val navController = rememberNavController()
    val viewModel: KeyboardThemeViewModel = hiltViewModel()

    Box {
        NavHost(
            navController = navController, startDestination = "main"
        ) {
            composable("main") { MainScreen({ showAd = true }, viewModel) }
        }
        if (showAd) {
            AdScreen(
                { viewModel.unlockTheme(); showAd = false; viewModel.showUnlockedDialog() },
                viewModel
            )
        }

    }
}