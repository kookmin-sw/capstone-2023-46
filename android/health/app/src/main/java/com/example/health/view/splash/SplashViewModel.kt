package com.example.health.view.splash

import androidx.lifecycle.ViewModel
import com.example.health.local.PrefDataStore

class SplashViewModel(
    private val dataStore: PrefDataStore
): ViewModel() {

    suspend fun hasLogin(): Boolean {
        return !dataStore.getAccessToken().isNullOrEmpty()
    }
}