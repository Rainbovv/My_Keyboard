package com.example.mykeyboard.ui.main.component.keyboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.mykeyboard.domain.model.KeyboardTheme


const val THEME_APPLIED_MESSAGE = "Theme Applied!"


@Composable
fun UnlockedKeyboardBox(theme: KeyboardTheme) {
    val context = LocalContext.current

    Box {
        Image(
            painter = painterResource(theme.imageRes),
            contentDescription = "Unlocked Keyboard Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { toastFun(context) })
        )
    }
}

fun toastFun(context: Context) {
    Toast.makeText(
        context,
        THEME_APPLIED_MESSAGE,
        Toast.LENGTH_SHORT
    ).show()
}