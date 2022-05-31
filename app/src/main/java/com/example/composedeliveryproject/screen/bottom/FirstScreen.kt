package com.example.composedeliveryproject.screen.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composedeliveryproject.navigation.DishesScr

@Composable
fun FirstScreen(mainController: NavHostController, bottomController: NavHostController) {
    val dishesController = rememberNavController()
    NavHost(navController = dishesController, startDestination = "dishes" ) {
        composable(DishesScr.DishesScreen.route) { DishesScreen(dishesController)}
        composable(DishesScr.DishesMenuScreen.route,
            arguments = listOf(
                navArgument("icon") {
                    type = NavType.StringType
                },
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("price") {
                    type = NavType.StringType
                }
            )
        ) {
            val icon = it.arguments?.getString("icon").toString()
            val name = it.arguments?.getString("name").toString()
            val price = it.arguments?.getString("price").toString()
            DishesMenuScreen(dishesController, mainController, icon, name, price)
        }
    }
}


