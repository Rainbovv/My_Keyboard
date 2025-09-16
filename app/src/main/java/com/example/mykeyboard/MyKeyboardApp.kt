package com.example.mykeyboard

import android.app.Application
import com.example.mykeyboard.data.repository.KeyboardThemeRepository
import com.example.mykeyboard.data.service.AdManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@HiltAndroidApp
@Module
@InstallIn(SingletonComponent::class)
class MyKeyboardApp : Application() {
    @Provides
    @Singleton
    fun provideThemeRepository(): KeyboardThemeRepository {
        return KeyboardThemeRepository()
    }

    @Provides
    @Singleton
    fun provideAdManager(): AdManager {
        return AdManager()
    }
}
