package com.example.health.view.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import kotlinx.datetime.LocalDate

@Composable
fun CalendarScreen() {
    val selectedDayEventList = remember { mutableStateListOf<KalendarEvent>() }
    val scrollState = rememberScrollState()
    val eventList = remember { mutableStateListOf<KalendarEvent>() }


    Surface {
        Column {
            Kalendar(
                kalendarType = KalendarType.Firey,
                kalendarEvents = listOf(
                    KalendarEvent(LocalDate(2023, 3, 24), "testEvent", "this is test"),
                    KalendarEvent(LocalDate(2023, 3, 25), "testEvent2")
                ),
                onCurrentDayClick = { day, eventList ->
                    val list = eventList.filter { it.date == day.localDate }
                    selectedDayEventList.clear()
                    selectedDayEventList.addAll(list)
                }
            )

            LazyColumn {
                items(selectedDayEventList) {
                    Text(text = it.eventName)
//                    Row {
//                        Text(text = it.eventName)
//                        Spacer(modifier = Modifier.width(10.dp))
//                        Text(text = it.eventDescription ?: "")
//                    }
                }
            }
        }
    }
}