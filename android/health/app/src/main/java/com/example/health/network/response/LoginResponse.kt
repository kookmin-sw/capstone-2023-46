package com.example.health.network.response

data class LoginResponse(
    val userId: Int,
    val nickname: String,
    val login: Boolean,
    val accessToken: String
) {
}