package com.example.health.view.calendar.diet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health.R
import com.example.health.component.EmptyDataView
import org.koin.androidx.compose.koinViewModel

@Composable
fun DietScreen(viewModel: DietViewModel = koinViewModel()) {
    val dietList = viewModel.dietList.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "식단 기록") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
//            EmptyDataView()
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                items(dietList) { diet ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = Color.LightGray
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = "${diet.title}(${diet.foodList.sumOf { it.calorie }}cal)")
                            diet.foodList.forEach { food ->
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "${food.name} (${food.calorie}cal)", fontSize = 12.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}