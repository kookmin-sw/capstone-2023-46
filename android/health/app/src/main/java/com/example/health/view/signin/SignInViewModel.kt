package com.example.health.view.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health.local.PrefDataStore
import com.example.health.network.ApiService
import com.example.health.network.request.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val apiService: ApiService,
    private val dataStore: PrefDataStore
): ViewModel() {
    val isLoginSuccess = MutableStateFlow(false)

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest(email, password))
                if (response.login) {
                    dataStore.setAccessToken(response.accessToken)
                }
                isLoginSuccess.emit(true)
            } catch (e: Exception) {
                isLoginSuccess.emit(false)
            }
        }
    }
}