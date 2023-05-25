package com.example.health.view.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshoe.kalendar.model.KalendarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class CalendarViewModel: ViewModel() {
    val selectedDayEventList = MutableStateFlow(emptyList<KalendarEvent>())
    val eventList = MutableStateFlow(emptyList<KalendarEvent>())


    fun init() {
        viewModelScope.launch {
            eventList.emit(
                listOf(
                    KalendarEvent(LocalDate(2023, 3, 24), "testEvent", "this is test"),
                    KalendarEvent(LocalDate(2023, 3, 25), "testEvent2")
                )
            )
        }
    }
}