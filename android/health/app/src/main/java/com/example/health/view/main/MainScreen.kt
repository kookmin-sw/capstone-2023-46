package com.example.health.view.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.health.view.calendar.CalendarScreen
import com.example.health.view.schdule.ScheduleScreen
import com.example.health.view.setting.SettingScreen

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val tapController = rememberNavController()
    val mainBottomNavItems = listOf(
        BottomItem.CalendarTap,
        BottomItem.ScheduleTap,
        BottomItem.SettingTap
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") }
            )
        },
        bottomBar = {
            BottomNavigation {
                val backstackEntry by tapController.currentBackStackEntryAsState()
                val currentRoute = backstackEntry?.destination?.route

                mainBottomNavItems.forEach {
                    BottomNavigationItem(
                        selected = (it.route == currentRoute),
                        onClick = {
                            tapController.navigate(it.route) {
                                popUpTo(tapController.graph.startDestinationId) {
                                    saveState = true
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = it.icon),
                                contentDescription = it.title
                            )
                        },
                        label = { Text(text = it.title) }
                    )
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.padding(it),
        ) {
            MainNavigationGraph(
                rootNavController = navController,
                navController = tapController
            )
        }
    }
}

private sealed class BottomItem(
    val title: String,
    val route: String,
    val icon: Int
) {
    object CalendarTap: BottomItem("캘린더", "calendar", com.example.health.R.drawable.ic_calendar)
    object ScheduleTap: BottomItem("스케쥴", "schedule", com.example.health.R.drawable.ic_schedule)
    object SettingTap: BottomItem("설정", "setting", com.example.health.R.drawable.ic_setting)
}

@Composable
fun MainNavigationGraph(
    rootNavController: NavHostController,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "calendar"
    ) {
        composable("calendar") {
            CalendarScreen(rootNavController)
        }
        composable("schedule") {
            ScheduleScreen()
        }
        composable("setting") {
            SettingScreen(rootNavController)
        }
    }
}