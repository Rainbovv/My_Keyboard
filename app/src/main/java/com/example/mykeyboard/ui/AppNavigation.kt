package com.example.mykeyboard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mykeyboard.ui.ad.AdScreen
import com.example.mykeyboard.ui.ad.AdViewModel
import com.example.mykeyboard.ui.main.KeyboardThemeViewModel
import com.example.mykeyboard.ui.main.MainScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val keyViewModel: KeyboardThemeViewModel = hiltViewModel()
    val adViewModel: AdViewModel = hiltViewModel()

    Box {
        NavHost(
            navController = navController, startDestination = "main"
        ) {
            composable("main") { MainScreen({ adViewModel.showAdd() }, keyViewModel) }
        }
        if (adViewModel.isAdShown()) {
            AdScreen(
                {
                    keyViewModel.unlockTheme();
                    adViewModel.hideAd();
                    keyViewModel.showUnlockedDialog()
                },
                adViewModel
            )
        }
    }
}