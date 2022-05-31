package com.example.composedeliveryproject.screen.bottom

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun DishesMenuScreen(
    dishesController: NavHostController,
    mainController: NavHostController,
    icon: String,
    name: String?,
    price: String?
) {
    val context = LocalContext.current
    Toast.makeText(context, name.toString(), Toast.LENGTH_SHORT).show()
}