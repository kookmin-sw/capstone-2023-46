package com.example.health.view.schdule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType

@Composable
fun ScheduleScreen() {
    Surface {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Kalendar(
                kalendarType = KalendarType.Oceanic(),
                kalendarEvents = listOf(),
                onCurrentDayClick = { day, eventList ->
                    val list = eventList.filter { it.date == day.localDate }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "스케쥴 변경하기")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "출석 인증하기")
            }
        }
    }
}