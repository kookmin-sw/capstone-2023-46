package com.example.health

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.health.view.calendar.diet.DietScreen
import com.example.health.view.calendar.photo.PhotoScreen
import com.example.health.view.calendar.record.RecordScreen
import com.example.health.view.main.MainScreen
import com.example.health.view.map.MapScreen
import com.example.health.view.routine.RoutineScreen
import com.example.health.view.signin.SignInScreen
import com.example.health.view.signup.SignUpScreen
import com.example.health.view.splash.SplashScreen

@Composable
fun NavigationScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "splash",
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("sign_in") { SignInScreen(navController) }
        composable("sign_up") { SignUpScreen() }
        composable("diet") { DietScreen() }
        composable("photo") { PhotoScreen() }
        composable("record") { RecordScreen(navController) }
        composable("routine") { RoutineScreen() }
        composable("map") { MapScreen() }
    }
}