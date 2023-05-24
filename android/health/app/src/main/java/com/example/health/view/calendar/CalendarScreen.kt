package com.example.health.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import org.koin.androidx.compose.koinViewModel

@Composable
fun CalendarScreen(
    navController: NavHostController,
    viewModel: CalendarViewModel = koinViewModel()
) {
    val selectedDayEventList = remember { mutableStateListOf<KalendarEvent>() }
    val scrollState = rememberScrollState()
    val eventList = remember { mutableStateListOf<KalendarEvent>() }
    val isDialogShow = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.init()
    }
    
    if (isDialogShow.value) {
        Dialog(onDismissRequest = { isDialogShow.value = false }) {
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(Color.White).padding(15.dp)
                ) {
                    Button(onClick = {
                        isDialogShow.value = false
                        navController.navigate("photo")
                    }) {
                        Text(text = "사진 기록")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        isDialogShow.value = false
                        navController.navigate("record")
                    }) {
                        Text(text = "운동 기록")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        isDialogShow.value = false
                        navController.navigate("diet")
                    }) {
                        Text(text = "식단 기록")
                    }
                }
            }

        }
    }

    Surface {
        Column {
            Kalendar(
                kalendarType = KalendarType.Firey,
                kalendarEvents = viewModel.eventList.collectAsState().value,
                onCurrentDayClick = { day, eventList ->
                    val list = eventList.filter { it.date == day.localDate }
                    selectedDayEventList.clear()
                    selectedDayEventList.addAll(list)
                    isDialogShow.value = true
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