package com.example.health.network.response

data class CalendarResponse(
    val calendar_id: Int,
    val data: String,
    val calorie: Int,
    val weight: Int,
    val hasCalorie: Boolean,
    val hasWeight: Boolean
) {
}