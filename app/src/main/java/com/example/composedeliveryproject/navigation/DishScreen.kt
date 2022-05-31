package com.example.composedeliveryproject.navigation

sealed class DishesScr(val route: String) {
    object DishesScreen: DishesScr("dishes_screen")
    object DishesMenuScreen: DishesScr("dishes_menu_screen/{icon}/{name}/{price}")

    fun passPar(icon: String, name: String, price: String) : String {
        return "dishes_menu_screen/$icon/$name/$price"
    }
}