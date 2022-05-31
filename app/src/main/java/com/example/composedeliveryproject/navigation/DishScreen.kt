package com.example.composedeliveryproject.navigation

sealed class DishesScr(val route: String) {
    object DishesScreen: DishesScr("dishes_screen")
    object DishesMenuScreen: DishesScr("dishes_menu_screen")

}