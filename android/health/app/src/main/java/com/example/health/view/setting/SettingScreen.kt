package com.example.health.view.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingScreen(
    navController: NavHostController,
    viewModel: SettingViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.isLogout.collect {
            if (it) {
                navController.navigate("sign_in") {
                    popUpTo("main") {
                        inclusive = true
                    }
                }
            }
        }
    }

    Surface {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
                
            Text(text = "등록된 헬스장")

            Spacer(modifier = Modifier.height(5.dp))

            Card(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                backgroundColor = Color.LightGray,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "서울특별시 중구 청계천로 86")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("map") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "헬스장 위치 변경하기")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.logout() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "로그아웃")
            }
        }
    }
}