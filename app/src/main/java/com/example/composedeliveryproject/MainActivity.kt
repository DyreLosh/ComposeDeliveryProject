package com.example.composedeliveryproject

import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composedeliveryproject.navigation.MainNavigation
import com.example.composedeliveryproject.ui.theme.ComposeDeliveryProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDeliveryProjectTheme {
                // A surface container using the 'background' color from the theme
                val connect = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connect.activeNetworkInfo
                MainNavigation(networkInfo)

            }
        }
    }
}

