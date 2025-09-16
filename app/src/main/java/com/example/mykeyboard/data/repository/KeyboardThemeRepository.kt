package com.example.mykeyboard.data.repository

import com.example.mykeyboard.R
import com.example.mykeyboard.domain.model.KeyboardTheme

const val KEY_CARD_PREFIX = "card_"

class KeyboardThemeRepository () {
    val themes = mutableSetOf<KeyboardTheme>()

    fun getDrawablesByPrefix(): List<Int> {
        val fields = R.drawable::class.java.fields
        return fields
            .filter { it.name.startsWith(KEY_CARD_PREFIX) }
            .mapNotNull {
                try {
                    it.getInt(null)
                } catch (e: Exception) {
                    null
                }
            }
    }

    init {
        getDrawablesByPrefix().forEachIndexed { index, it ->
            themes.add(KeyboardTheme(index, it, index % 3 == 0))
        }
    }

    fun getAll(): List<KeyboardTheme> {
        return themes.toList()
    }
}