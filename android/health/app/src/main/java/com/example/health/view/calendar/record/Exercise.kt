package com.example.health.view.calendar.record

data class Exercise(
    val id: Long,
    val name: String,
    val set: Int,
    val weights: List<Int>,
    val date: String
) {
}