package com.example.health.view.calendar.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecordViewModel: ViewModel() {
    val exerciseList = MutableStateFlow(listOf<Exercise>())

    init {
        viewModelScope.launch {
            val list = listOf(
                Exercise(
                    id = 1,
                    name = "벤치",
                    set = 2,
                    weights = listOf(1, 2, 3),
                    date = ""
                ),
                Exercise(
                    id = 2,
                    name = "벤치",
                    set = 2,
                    weights = listOf(1, 2, 3),
                    date = ""
                )
            )
            exerciseList.tryEmit(list)
        }
    }
}