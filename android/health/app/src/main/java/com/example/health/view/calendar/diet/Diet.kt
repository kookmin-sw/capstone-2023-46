package com.example.health.view.calendar.diet

data class Diet(
    val title: String,
    val foodList: List<Food>
) {
}

data class Food(
    val name: String,
    val calorie: Int
)