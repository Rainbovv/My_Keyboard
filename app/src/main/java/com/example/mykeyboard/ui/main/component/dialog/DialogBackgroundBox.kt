package com.example.mykeyboard.ui.main.component.dialog

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.copy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.mykeyboard.R
import com.example.mykeyboard.ui.theme.RoundCornerDp
import com.example.mykeyboard.ui.theme.UnlockDialogBackgroundBorderColor
import com.example.mykeyboard.ui.theme.getGradientColorArray

@Composable
fun DialogBackgroundBox(dialogWidth: Dp, imageLoader: ImageLoader) {
    Box(
        Modifier
            .background(
                brush = Brush.verticalGradient(colorStops = getGradientColorArray()),
                shape = RoundedCornerShape(topStart = RoundCornerDp, topEnd = RoundCornerDp)
            )
            .width(dialogWidth)
            .aspectRatio(1.5f)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = R.raw.dialog_pattern, // or URL
                imageLoader = imageLoader
            ),
            contentDescription = "Dialog Pattern",
            modifier = Modifier.alpha(0.05f)
        )
        Canvas(modifier = Modifier.fillMaxSize()) {
            val waveHeight = size.height * 0.7f
            val waveLength = size.width / 2

            val borderPath = Path().apply {
                moveTo(0f, waveHeight)
                quadraticTo(
                    waveLength / 2, waveHeight - 100,
                    waveLength, waveHeight
                )
                quadraticTo(
                    waveLength + waveLength / 2, waveHeight + 120,
                    size.width, waveHeight
                )
            }

            val whitePath = borderPath.copy().apply {
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            drawPath(whitePath, color = Color.White)

            drawPath(
                path = borderPath,
                color = UnlockDialogBackgroundBorderColor,
                style = Stroke(width = 3.dp.toPx())
            )
        }

    }
}
