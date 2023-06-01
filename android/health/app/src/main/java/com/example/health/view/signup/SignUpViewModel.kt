package com.example.health.view.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health.network.ApiService
import com.example.health.network.request.SignUpRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val apiService: ApiService
): ViewModel() {

    val errorMessage = MutableStateFlow("")

    fun signUp(
        email: String,
        nickName: String,
        password: String,
        passwordRe: String
    ) {
        viewModelScope.launch {
            if (email.isEmpty() || nickName.isEmpty() || password.isEmpty() || passwordRe.isEmpty()) {
                errorMessage.tryEmit("항목을 모두 입력해 주세요.")
            } else if (password != passwordRe) {
                errorMessage.tryEmit("비밀번호를 확인해주세요.")
            } else {
                errorMessage.tryEmit("")
                try {
                    val response = apiService.signUp(
                        SignUpRequest(email, nickName, password)
                    )

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}