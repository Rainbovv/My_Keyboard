package com.example.mykeyboard.domain.model

data class KeyboardTheme(
    val id: Int,
    val imageRes: Int,
    var isLocked: Boolean = false,
)
