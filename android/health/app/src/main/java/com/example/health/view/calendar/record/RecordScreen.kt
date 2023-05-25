package com.example.health.view.calendar.record

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
import androidx.navigation.NavHostController
import com.example.health.R
import com.example.health.component.EmptyDataView
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecordScreen(
    navController: NavHostController,
    viewModel: RecordViewModel = koinViewModel()
) {
    val exerciseList = viewModel.exerciseList.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "운동 기록") },
                actions = {
                    IconButton(onClick = { navController.navigate("routine") }) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            if (exerciseList.isEmpty()) {
                EmptyDataView()
            } else {
                LazyColumn(modifier = Modifier.padding(20.dp)) {
                    items(exerciseList) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = Color.LightGray
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = it.name)
                                Text(text = it.set.toString() + "세트")
                                Text(text = "중량 : " + it.weights.joinToString())
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}