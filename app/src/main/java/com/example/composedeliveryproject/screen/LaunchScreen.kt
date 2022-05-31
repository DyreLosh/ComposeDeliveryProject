package com.example.composedeliveryproject.screen

import android.net.NetworkInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedeliveryproject.R
import com.example.composedeliveryproject.navigation.MainScreen
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(mainController: NavHostController, networkInfo: NetworkInfo?) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier
            .align(Alignment.Center)
            .padding(50.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ellipe),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
            Text(
                text = "Delivery App",
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 80.dp)


            )
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp)


            )
            LaunchedEffect(key1 = true) {
                if(networkInfo != null && networkInfo.isConnected == true) {
                    delay(2000)
                    mainController.navigate(MainScreen.OnBoardingScreen.route)
                }
                else {
                    delay(2000)

                    mainController.navigate(MainScreen.NotInternetScreen.route)
                }

            }
        }
    }
}