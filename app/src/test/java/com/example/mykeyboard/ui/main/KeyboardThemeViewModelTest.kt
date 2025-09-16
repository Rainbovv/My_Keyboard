package com.example.mykeyboard.ui.main

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import com.example.mykeyboard.data.repository.KeyboardThemeRepository
import com.example.mykeyboard.domain.model.KeyboardTheme
import org.junit.Assert
import org.junit.Test

class KeyboardThemeViewModelTest {

    private fun buildViewModelWithThemes(
        themes: List<KeyboardTheme> = listOf(KeyboardTheme(1, 111, isLocked = true))
    ): KeyboardThemeViewModel {
        return KeyboardThemeViewModel(KeyboardThemeRepository()).apply {
            this.themes.clear()
            themes.forEach { theme -> this.themes[theme.id] = theme }
            selectTheme(themes.first())
        }
    }

    @Test
    fun `showLockedDialog should lock dialog, show it, and hide close button`() {
        // Given
        val viewModel = buildViewModelWithThemes()
        viewModel.showCloseButton()
        
        // When
        viewModel.showLockedDialog()

        // Then
        with(viewModel) {
            Assert.assertTrue("Dialog should be locked", isDialogLocked.value)
            Assert.assertTrue("Dialog should be shown", isDialogShown.value)
            Assert.assertFalse("Close button should be hidden", isCloseButtonShown.value)
        }
    }

    @Test
    fun `showUnlockedDialog should unlock dialog, show it, and show close button`() {
        // Given
        val viewModel = buildViewModelWithThemes()

        // When
        viewModel.showUnlockedDialog()

        // Then
        with(viewModel) {
            Assert.assertFalse("Dialog should be unlocked", isDialogLocked.value)
            Assert.assertTrue("Dialog should be shown", isDialogShown.value)
            Assert.assertTrue("Close button should be visible", isCloseButtonShown.value)
        }
    }

    @Test
    fun `hideDialog should hide dialog and reset to locked state`() {
        // Given
        val viewModel = buildViewModelWithThemes()
        viewModel.showUnlockedDialog()

        // When
        viewModel.hideDialog()

        // Then
        with(viewModel) {
            Assert.assertTrue("Dialog should be locked after hiding", isDialogLocked.value)
            Assert.assertFalse("Dialog should be hidden", isDialogShown.value)
        }
    }

    @Test
    fun `getUnlockDimensions should calculate dimensions based on keyboard icon size and density`() {
        // Given
        val viewModel = buildViewModelWithThemes()
        val testIconSize = IntSize(1000, 500)
        val density = Density(1f)
        viewModel.setKeyboardIconSize(testIconSize)

        // When
        val widthDp = viewModel.getUnlockWidth(density)
        val heightDp = viewModel.getUnlockHeight(density)

        // Then
        val expectedWidth = testIconSize.width * 0.42f
        val expectedHeight = testIconSize.height * 0.18f
        
        Assert.assertEquals(
            "Width should be 42% of icon width",
            expectedWidth,
            widthDp.value,
            0.001f
        )
        Assert.assertEquals(
            "Height should be 18% of icon height",
            expectedHeight,
            heightDp.value,
            0.001f
        )
    }

    @Test
    fun `selectTheme should update the selected theme and its properties`() {
        // Given
        val themes = listOf(
            KeyboardTheme(1, 111, isLocked = true),
            KeyboardTheme(2, 222, isLocked = false)
        )
        val viewModel = buildViewModelWithThemes(themes)
        val newTheme = themes[1]

        // When
        viewModel.selectTheme(newTheme)

        // Then
        with(viewModel.selectedTheme.value) {
            Assert.assertEquals(
                "Selected theme ID should be updated",
                newTheme.id,
                id
            )
            Assert.assertFalse(
                "Selected theme should be unlocked",
                isLocked
            )
        }
    }

    @Test
    fun `unlockTheme should mark the selected theme as unlocked in themes map`() {
        // Given
        val lockedTheme = KeyboardTheme(10, 1010, isLocked = true)
        val viewModel = buildViewModelWithThemes(listOf(lockedTheme))
        viewModel.selectTheme(lockedTheme)

        // When
        viewModel.unlockTheme()

        // Then
        val updatedTheme = viewModel.themes[lockedTheme.id]
        Assert.assertNotNull("Theme should exist in the map", updatedTheme)
        Assert.assertFalse("Theme should be unlocked", updatedTheme!!.isLocked)
    }
}