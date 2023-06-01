package com.example.health.view.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = koinViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.isLoginSuccess.collect {
            if (it) {
                navController.navigate("main") {
                    popUpTo("sign_in") {
                        inclusive = true
                    }
                }
            }
        }
    }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                label = { Text(text = "Email") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Password") }
            )

            Spacer(modifier = Modifier.height(20.dp))
            
            Button(onClick = {
                viewModel.signIn(email, password)
//                navController.navigate("main") {
//                    popUpTo("sign_in") {
//                        inclusive = true
//                    }
//                }
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