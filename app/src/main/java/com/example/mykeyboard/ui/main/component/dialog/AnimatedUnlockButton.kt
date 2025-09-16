package com.example.mykeyboard.ui.main.component.dialog

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.mykeyboard.R
import com.example.mykeyboard.ui.theme.UnlockButtonBackgroundColor
import com.example.mykeyboard.ui.theme.getGradientColorArray

@Composable
fun AnimatedUnlockButton(onClick: () -> Unit, imageLoader: ImageLoader) {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            scale.animateTo(1.10f, animationSpec = tween(1000))
            scale.animateTo(1f, animationSpec = tween(1000))
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .scale(scale.value)
            .fillMaxWidth(0.8f)
            .dropShadow(shape = RoundedCornerShape(25.dp)) {
                color = UnlockButtonBackgroundColor
                offset = Offset(x = 0f, y = 10f)
                radius = 0f
            }
            .background(
                brush = Brush.linearGradient(colorStops = getGradientColorArray()),
                shape = RoundedCornerShape(25.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.height(50.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = R.raw.unlock_button_icon, // or URL
                    imageLoader = imageLoader
                ),
                contentDescription = "Unlock Button Icon",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                "Unlock for free",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}