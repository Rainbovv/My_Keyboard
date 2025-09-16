package com.example.mykeyboard.ui.ad

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mykeyboard.data.service.AdManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


@HiltViewModel
class AdViewModel @Inject constructor(private val adManager: AdManager): ViewModel() {

    var isAdShown = mutableStateOf(false)

    fun isAdShown(): Boolean {
        return isAdShown.value
    }

    fun showAdd() {
        isAdShown.value = true
    }

    fun hideAd() {
        isAdShown.value = false
    }

    fun getBottomAdImageRes(): Int {
        return adManager.getBottomAdRes()
    }

    fun getBeautifulAdImageRes(): Int {
        return adManager.getBeautifulAdRes()
    }
}