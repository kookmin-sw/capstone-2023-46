package com.example.health.view.schdule

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType

@Composable
fun ScheduleScreen() {
    Surface {
        Column {
            Kalendar(
                kalendarType = KalendarType.Oceanic(),
                kalendarEvents = listOf(),
                onCurrentDayClick = { day, eventList ->
                    val list = eventList.filter { it.date == day.localDate }
                }
            )
        }
    }
}