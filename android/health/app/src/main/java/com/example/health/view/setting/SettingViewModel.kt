package com.example.health.view.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health.local.PrefDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingViewModel(
    private val dataStore: PrefDataStore
): ViewModel() {
    val isLogout = MutableStateFlow(false)

    fun logout() {
        viewModelScope.launch {
            dataStore.setAccessToken("")
            isLogout.emit(true)
        }
    }
}