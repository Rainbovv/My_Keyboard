package com.example.mykeyboard.ui.main.component.unlock

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import com.example.mykeyboard.ui.main.KeyboardThemeViewModel
import com.example.mykeyboard.ui.theme.DialogKeyboardBorder
import com.example.mykeyboard.ui.theme.ForFreeColor
import com.example.mykeyboard.ui.theme.RoundCornerDp

@Composable
fun DialogContent(
    onUnlock: () -> Unit,
    imageLoader: ImageLoader,
    viewModel: KeyboardThemeViewModel
) {
    Column(
        modifier = Modifier.padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .padding(top = 30.dp, start = 20.dp, end = 20.dp)
                .clip(RoundedCornerShape(RoundCornerDp))
                .aspectRatio(1.8f)
                .border(
                    width = 2.dp,
                    color = DialogKeyboardBorder,
                    shape = RoundedCornerShape(RoundCornerDp)
                )
        ) {
            Image(
                painter = painterResource(viewModel.selectedTheme.value.imageRes),
                contentDescription = "Keyboard Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (viewModel.isDialogLocked.value) {
            Text(
                text = "Watch the ad and get this keyboard",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "FOR FREE",
                color = ForFreeColor,
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            AnimatedUnlockButton(onClick = { viewModel.hideDialog(); onUnlock() }, imageLoader)
        }
        else {
            Text(
                text = "Keyboard Unlocked",
                color = ForFreeColor,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 50.dp)
            )
        }
    }
}