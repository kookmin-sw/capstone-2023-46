package com.example.health

sealed class Navigation(
    val route: String
) {
    object Splash : Navigation("splash")
    object Main : Navigation("main")
    object Calendar : Navigation("calendar")
}