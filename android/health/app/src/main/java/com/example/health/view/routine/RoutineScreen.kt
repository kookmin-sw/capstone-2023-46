package com.example.health.view.routine

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.health.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutineScreen(
    viewModel: RoutineViewModel = koinViewModel()
) {
    val routineList = viewModel.routineList.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "나만의 루틴") },
                actions = {
                    IconButton(onClick = { viewModel.test() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                items(routineList) {
                    Card(
                        onClick = { viewModel.addRecord(it) },
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = Color.LightGray
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = it.name)
                            Spacer(modifier = Modifier.height(5.dp))
                            it.exerciseList.forEach { routineExercise ->
                                Text(text = routineExercise.name + " " + routineExercise.set + "세트")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}