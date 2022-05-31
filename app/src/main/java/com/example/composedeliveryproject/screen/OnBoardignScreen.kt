package com.example.composedeliveryproject.screen

import android.net.NetworkInfo
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedeliveryproject.R
import com.example.composedeliveryproject.navigation.MainScreen
import com.example.composedeliveryproject.ui.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(mainController: NavHostController, networkInfo: NetworkInfo?) {
    val context = LocalContext.current
    val pagerSelect = rememberPagerState()
    val scope = rememberCoroutineScope()
    if(networkInfo != null && networkInfo.isConnected == true) {
        Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show()
    }
    else{
        Toast.makeText(context, "bad", Toast.LENGTH_SHORT).show()

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange)
    ) {
        HorizontalPager(state = pagerSelect, count = 2) { index ->
            Column {
                when (index) {
                    0 -> {
                        OnBoardingTwo()
                    }
                    1 -> {
                        OnBoardOne(mainController)
                    }
                }

            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerSelect, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
    }

}

@Composable
fun OnBoardingTwo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange)
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "Delivery",
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        )
    }
}


    @Composable
    fun OnBoardOne(mainController: NavHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Orange)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                contentScale = ContentScale.FillWidth
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(
                        Alignment.BottomCenter
                    )
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp)
            ) {
                Button(
                    onClick = { mainController.navigate(MainScreen.LoginScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Sign Up", Modifier.padding(vertical = 10.dp))
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { mainController.navigate(MainScreen.RegisterScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Sign In", Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
