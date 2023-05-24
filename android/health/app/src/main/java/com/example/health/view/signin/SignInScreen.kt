package com.example.health.view.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SignInScreen(navController: NavHostController) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = id,
                onValueChange = { id = it },
                label = { Text(text = "ID") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "PASSWORD") }
            )

            Spacer(modifier = Modifier.height(20.dp))
            
            Button(onClick = {
                navController.navigate("main") {
                    popUpTo("sign_in") {
                        inclusive = true
                    }
                }
            }) {
                Text(text = "로그인")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                navController.navigate("sign_up")
            }) {
                Text(text = "회원가입")
            }
        }
    }
}