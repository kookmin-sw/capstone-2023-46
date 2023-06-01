package com.example.health.view.calendar.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecordViewModel(
    private val apiService: ApiService
): ViewModel() {
    val exerciseList = MutableStateFlow(listOf<Exercise>())

    init {
        load()
    }

    fun add() {

    }

    fun load() {
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