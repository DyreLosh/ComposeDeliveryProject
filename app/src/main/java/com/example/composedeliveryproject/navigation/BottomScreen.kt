package com.example.composedeliveryproject.navigation

import com.example.composedeliveryproject.R

sealed class BottomScreen(val image: Int, val route: String, val title: String) {
    object FirstScreen: BottomScreen(R.drawable.ic_launcher_background, "first", "First")
    object TwoScreen: BottomScreen(R.drawable.ic_launcher_background, "two", "Two")
    object ThreeScreen: BottomScreen(R.drawable.ic_launcher_background, "three", "Three")
    object FourScreen: BottomScreen(R.drawable.ic_launcher_background, "four", "Four")
}
