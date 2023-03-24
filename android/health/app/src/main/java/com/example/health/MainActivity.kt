package com.example.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.health.ui.theme.HealthTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {
                rememberSystemUiController().apply { ConfigSystemUi(controller = this) }
                val navController = rememberNavController()
                NavigationScreen(navController = navController)
            }
        }
    }
}

@Composable
private fun ConfigSystemUi(controller: SystemUiController) {
    controller.apply {
//        setSystemBarsColor(Color.White)
    }
}