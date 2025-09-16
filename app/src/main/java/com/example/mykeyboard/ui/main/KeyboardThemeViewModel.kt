package com.example.mykeyboard.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.example.mykeyboard.data.repository.KeyboardThemeRepository
import com.example.mykeyboard.domain.model.KeyboardTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class KeyboardThemeViewModel @Inject constructor(repository: KeyboardThemeRepository) :
    ViewModel() {

    val themes: SnapshotStateMap<Int, KeyboardTheme> =
        repository.getAll().map { it.id to it }.toMutableStateMap()
    val keyboardIconSize = mutableStateOf(IntSize.Companion.Zero)
    var selectedTheme = mutableStateOf(themes.values.first())
    var isDialogShown = mutableStateOf(false)
    var isDialogLocked = mutableStateOf(true)
    var isCloseButtonShown = mutableStateOf(false)


    fun isCloseButtonShown(): Boolean {
        return isCloseButtonShown.value
    }

    fun getThemes(): List<KeyboardTheme> {
        return themes.values.toList()
    }

    fun showLockedDialog() {
        hideCloseButton()
        isDialogLocked.value = true
        isDialogShown.value = true
    }

    fun showUnlockedDialog() {
        isDialogLocked.value = false
        isDialogShown.value = true
        isCloseButtonShown.value = true
    }

    fun hideDialog() {
        isDialogLocked.value = true
        isDialogShown.value = false
    }

    fun showCloseButton() {
        isCloseButtonShown.value = true
    }

    fun hideCloseButton() {
        isCloseButtonShown.value = false
    }

    fun setKeyboardIconSize(size: IntSize) {
        keyboardIconSize.value = size
    }

    fun selectTheme(theme: KeyboardTheme) {
        selectedTheme.value = theme
    }

    fun getUnlockWidth(density: Density): Dp {
        return with(density) { keyboardIconSize.value.width.toDp() } * 0.42f
    }

    fun getUnlockHeight(density: Density): Dp {
        return with(density) { keyboardIconSize.value.height.toDp() } * 0.18f
    }

    fun unlockTheme() {
        val unlockedTheme = selectedTheme.value.copy(isLocked = false)
        themes[unlockedTheme.id] = unlockedTheme
    }
}