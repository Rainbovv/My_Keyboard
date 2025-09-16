package com.example.mykeyboard.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.mykeyboard.ui.ad.AdViewModel
import com.example.mykeyboard.ui.theme.BarBackgroundColor

@Composable
fun BottomBar(viewModel: AdViewModel = hiltViewModel()) {
    BottomAppBar(
        containerColor = BarBackgroundColor,
        contentPadding = PaddingValues(),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(viewModel.getBottomAdImageRes()),
                contentDescription = "BottomBar Add Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp, start = 20.dp, top = 3.dp, bottom = 3.dp)
            )
        }
    }
}