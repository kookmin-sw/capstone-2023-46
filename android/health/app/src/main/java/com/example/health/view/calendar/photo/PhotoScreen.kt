package com.example.health.view.calendar.photo

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.health.R
import com.example.health.component.EmptyDataView

@Composable
fun PhotoScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "사진 기록") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
                    }
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            EmptyDataView()
        }
    }
}