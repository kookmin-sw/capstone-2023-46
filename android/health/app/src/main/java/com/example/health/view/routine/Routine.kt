package com.example.health.view.routine

data class Routine(
    val name: String,
    val exerciseList: List<RoutineExercise>
)

data class RoutineExercise(
    val name: String,
    val set: Int
)