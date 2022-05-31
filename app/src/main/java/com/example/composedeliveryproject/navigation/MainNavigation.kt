package com.example.composedeliveryproject.navigation

import android.net.NetworkInfo
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedeliveryproject.screen.*

@Composable
fun MainNavigation(networkInfo: NetworkInfo?) {
    val mainController = rememberNavController()

    NavHost(navController = mainController, startDestination = MainScreen.LaunchScreen.route) {
        composable(MainScreen.LaunchScreen.route) { LaunchScreen(mainController, networkInfo)}
        composable(MainScreen.LoginScreen.route) { LoginScreen(mainController)}
        composable(MainScreen.RegisterScreen.route) { RegisterScreen(mainController)}
        composable(MainScreen.OnBoardingScreen.route) { OnBoardingScreen(mainController, networkInfo)}
        composable(MainScreen.NotInternetScreen.route) { NotInternetScreen(mainController)}
        composable(MainScreen.MainStartScreen.route) { MainStartScreen(mainController)}
    }
}

sealed class MainScreen(val route: String) {
    object LaunchScreen: MainScreen("launch")
    object OnBoardingScreen: MainScreen("onboarding")
    object LoginScreen: MainScreen("login")
    object RegisterScreen: MainScreen("register")
    object NotInternetScreen: MainScreen("notInternet")
    object MainStartScreen: MainScreen("main")

}