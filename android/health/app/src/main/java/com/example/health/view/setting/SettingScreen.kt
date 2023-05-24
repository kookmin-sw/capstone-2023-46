package com.example.health.view.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingScreen() {
    Surface {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                backgroundColor = Color.LightGray,
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "헬스장 등록하기")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "로그아웃")
            }
        }
    }
}