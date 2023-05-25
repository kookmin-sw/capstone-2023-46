package com.example.health.view.calendar.diet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DietViewModel: ViewModel() {
    val dietList = MutableStateFlow(listOf<Diet>())

    init {
        viewModelScope.launch {
            val list = listOf(
                Diet(
                    "아침",
                    listOf(
                        Food("삶은 달걀", 68),
                        Food("바나나", 93),
                    )
                ),
                Diet(
                    "점심",
                    listOf(
                        Food("김밥", 310)
                    )
                ),
                Diet(
                    "저녁",
                    listOf(
                        Food("고구마", 270),
                        Food("양배추", 70),
                    )
                )
            )
            dietList.tryEmit(list)
        }
    }
}