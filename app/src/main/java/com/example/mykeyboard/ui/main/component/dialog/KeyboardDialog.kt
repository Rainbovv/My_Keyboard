package com.example.mykeyboard.ui.main.component.dialog

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.ImageLoader
import com.example.mykeyboard.ui.main.KeyboardThemeViewModel
import com.example.mykeyboard.ui.theme.GradientBorderStroke
import com.example.mykeyboard.ui.theme.RoundCornerDp
import kotlinx.coroutines.delay

@Composable
fun KeyboardDialog(
    onUnlock: () -> Unit,
    imageLoader: ImageLoader,
    viewModel: KeyboardThemeViewModel
) {
    val windowSize = LocalWindowInfo.current.containerSize
    val screenWidth = with(LocalDensity.current) { windowSize.width.toDp() }

    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val dialogWidth = if (isPortrait) screenWidth * 0.9f else screenWidth * 0.38f

    LaunchedEffect(Unit) {
        delay(5000)
        viewModel.showCloseButton()
    }

    Dialog(
        onDismissRequest = { if (viewModel.isCloseButtonShown()) viewModel.hideDialog() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .width(dialogWidth)
                .aspectRatio(0.9f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(RoundCornerDp)
                )
                .border(
                    GradientBorderStroke,
                    RoundedCornerShape(RoundCornerDp)
                )
        ) {
            DialogBackgroundBox(dialogWidth, imageLoader)

            if (viewModel.isCloseButtonShown.value) {
                IconButton(
                    onClick = { viewModel.hideDialog() },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Button",
                        tint = Color.White
                    )
                }
            }
            DialogContent(onUnlock, imageLoader, viewModel)
        }
    }
}



