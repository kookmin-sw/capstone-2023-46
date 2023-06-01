package com.example.health.view.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = koinViewModel()
) {

    val scale = remember { Animatable(0.0f) }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(500)

        if (viewModel.hasLogin()) {
            navController.navigate("main") {
                popUpTo("splash") {
                    inclusive = true
                }
            }
        } else {
            navController.navigate("sign_in") {
                popUpTo("splash") {
                    inclusive = true
                }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {

    }
}