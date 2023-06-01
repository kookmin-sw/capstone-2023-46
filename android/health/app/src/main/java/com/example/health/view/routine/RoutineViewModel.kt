package com.example.health.view.routine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RoutineViewModel(
    private val apiService: ApiService
): ViewModel() {
    val routineList = MutableStateFlow(listOf<Routine>())

    init {
//        viewModelScope.launch {
//            val list = listOf(
//                Routine(
//                    name = "상체",
//                    exerciseList = listOf(
//                        RoutineExercise("벤치", 5),
//                        RoutineExercise("푸쉬업", 7),
//                    )
//                ),
//                Routine(
//                    name = "하체",
//                    exerciseList = listOf(
//                        RoutineExercise("스쿼트", 5),
//                        RoutineExercise("레그익스텐션", 7),
//                    )
//                )
//            )
//            routineList.tryEmit(list)
//        }
    }

    private fun fetch() {
        viewModelScope.launch {
            apiService.getRoutineList()
        }
    }

    fun test() {
        viewModelScope.launch {
            val list = listOf(
                Routine(
                    name = "상체",
                    exerciseList = listOf(
                        RoutineExercise("벤치", 5),
                        RoutineExercise("푸쉬업", 7),
                    )
                ),
                Routine(
                    name = "하체",
                    exerciseList = listOf(
                        RoutineExercise("스쿼트", 5),
                        RoutineExercise("레그익스텐션", 7),
                    )
                )
            )
            routineList.tryEmit(list)
        }
    }

    fun addRecord(routine: Routine) {

    }
}