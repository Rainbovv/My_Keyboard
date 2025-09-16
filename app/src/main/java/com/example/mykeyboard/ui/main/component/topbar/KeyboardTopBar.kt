package com.example.mykeyboard.ui.main.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.mykeyboard.R
import com.example.mykeyboard.ui.theme.BarBackgroundColor
import com.example.mykeyboard.ui.theme.GradientBorderStroke

const val TRY_KEYBOARD = "Try your keyboard…"

@Composable
fun KeyboardTopBar(imageLoader: ImageLoader) {
    val input = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .background(BarBackgroundColor)
            .padding(top = 60.dp)

    ) {
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(265.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = R.raw.title_back, // or URL
                    imageLoader = imageLoader
                ),
                contentDescription = "Title Back",
            )
            Image(
                painter = rememberAsyncImagePainter(
                    model = R.raw.title_front, // or URL
                    imageLoader = imageLoader
                ),
                contentDescription = "Title Front",
            )
        }
        OutlinedTextField(
            singleLine = true,
            value = input.value,
            onValueChange = { input.value = it },
            placeholder = { Text(TRY_KEYBOARD) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    GradientBorderStroke,
                    RoundedCornerShape(25.dp)
                )
                .clip(RoundedCornerShape(25.dp)),
        )
    }
}
