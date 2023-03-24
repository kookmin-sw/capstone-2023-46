package com.example.health

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.health.view.calendar.CalendarScreen
import com.example.health.view.main.MainScreen
import com.example.health.view.splash.SplashScreen

@Composable
fun NavigationScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.Splash.route,
    ) {
        composable(Navigation.Splash.route) {
            SplashScreen(navController)
        }

        composable(Navigation.Main.route) {
            MainScreen(navController)
        }

        composable(Navigation.Calendar.route) {
            CalendarScreen()
        }
    }
}