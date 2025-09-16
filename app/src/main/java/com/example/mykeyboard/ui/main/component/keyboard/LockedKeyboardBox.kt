package com.example.mykeyboard.ui.main.component.keyboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.mykeyboard.R
import com.example.mykeyboard.domain.model.KeyboardTheme
import com.example.mykeyboard.ui.main.KeyboardThemeViewModel
import com.example.mykeyboard.ui.theme.RoundCornerDp
import com.example.mykeyboard.ui.theme.getGradientColorArray


@Composable
fun LockedKeyboardBox(
    theme: KeyboardTheme,
    imageLoader: ImageLoader,
    viewModel: KeyboardThemeViewModel,
) {
    val density = LocalDensity.current

    Box {
        Image(
            painter = painterResource(theme.imageRes),
            contentDescription = "Locked Keyboard Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned(onGloballyPositioned = { c ->
                    viewModel.setKeyboardIconSize(c.size)
                })
                .clickable(onClick = {
                    viewModel.selectTheme(theme)
                    viewModel.showLockedDialog()
                })
        )
        Box(
            Modifier
                .align(Alignment.TopEnd)
                .background(
                    brush = Brush.linearGradient(colorStops = getGradientColorArray()),
                    shape = RoundedCornerShape(
                        bottomStart = RoundCornerDp,
                        topEnd = RoundCornerDp
                    )
                )
                .size(
                    height = viewModel.getUnlockHeight(density),
                    width = viewModel.getUnlockWidth(density)
                ),
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = R.raw.unlock_icon,
                    imageLoader = imageLoader
                ),
                contentDescription = "Unlock Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

