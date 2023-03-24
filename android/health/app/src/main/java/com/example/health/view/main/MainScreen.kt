package com.example.health.view.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.health.Navigation

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Column {
        Button(onClick = { navController.navigate(Navigation.Calendar.route) }) {
            Text(text = "go to calendar")
        }
    }
}