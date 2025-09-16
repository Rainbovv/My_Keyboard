package com.example.mykeyboard.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

val RoundCornerDp = 16.dp

val GradientBorderStroke = BorderStroke(
    width = 1.dp,
    brush = Brush.linearGradient(colorStops = getGradientColorArray()),
)