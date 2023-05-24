package com.example.health.view.routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RoutineViewModel: ViewModel() {
    val routineList = MutableStateFlow(listOf<Routine>())

    fun test() {
        viewModelScope.launch {
            val list = listOf(
                Routine(
                    name = "상체",
                    exerciseList = listOf(
                        RoutineExercise("벤치", 5),
                        RoutineExercise("푸쉬업", 7),
                    )
                )
            )
            routineList.tryEmit(list)
        }
    }

    fun addRecord(routine: Routine) {

    }
}