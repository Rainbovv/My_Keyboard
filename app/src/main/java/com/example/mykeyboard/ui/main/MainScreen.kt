package com.example.mykeyboard.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.example.mykeyboard.ui.main.component.BottomBar
import com.example.mykeyboard.ui.main.component.keyboard.LockedKeyboardBox
import com.example.mykeyboard.ui.main.component.keyboard.UnlockedKeyboardBox
import com.example.mykeyboard.ui.main.component.topbar.KeyboardTopBar
import com.example.mykeyboard.ui.main.component.dialog.KeyboardDialog
import com.example.mykeyboard.ui.theme.ContentBackgroundColor
import com.example.mykeyboard.ui.theme.GradientBorderStroke
import com.example.mykeyboard.ui.theme.RoundCornerDp


@Composable
fun MainScreen(
    onUnlock: () -> Unit,
    viewModel: KeyboardThemeViewModel
) {
    val imageLoader = ImageLoader
        .Builder(LocalContext.current)
        .components { add(SvgDecoder.Factory()) }
        .build()

    Scaffold(
        bottomBar = { BottomBar() }) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .background(ContentBackgroundColor)
                .fillMaxSize()
                .onGloballyPositioned(onGloballyPositioned = { c ->
                    viewModel.keyboardIconSize.value = c.size
                })
                .padding(innerPadding),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            stickyHeader {
                KeyboardTopBar(imageLoader)
            }
            itemsIndexed(viewModel.getThemes()) { index, item ->
                val paddingValues =
                    if (index % 2 == 0) PaddingValues(start = 10.dp) else PaddingValues(end = 10.dp)

                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .clip(RoundedCornerShape(RoundCornerDp))
                        .aspectRatio(1.5f)
                        .border(
                            GradientBorderStroke, RoundedCornerShape(RoundCornerDp)
                        )
                ) {
                    if (item.isLocked) LockedKeyboardBox(item, imageLoader, viewModel)
                    else UnlockedKeyboardBox(item)
                }
            }
        }

        if (viewModel.isDialogShown.value) {
            KeyboardDialog(onUnlock, imageLoader, viewModel)
        }
    }
}





