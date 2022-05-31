package com.example.composedeliveryproject.screen

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composedeliveryproject.navigation.BottomScreen
import com.example.composedeliveryproject.screen.bottom.FirstScreen
import com.example.composedeliveryproject.screen.bottom.ThreeScreen
import com.example.composedeliveryproject.screen.bottom.TwoScreen
import com.example.composedeliveryproject.ui.theme.Orange

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainStartScreen(mainController: NavHostController) {
    val bottomController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNav(bottomController)}
    ) {
        NavHost(navController = bottomController, startDestination = BottomScreen.FirstScreen.route) {
            composable(BottomScreen.FirstScreen.route) { FirstScreen(mainController, bottomController)}
            composable(BottomScreen.TwoScreen.route) { TwoScreen()}
            composable(BottomScreen.ThreeScreen.route) { ThreeScreen()}
            composable(BottomScreen.FourScreen.route) {}
        }
    }
}
@Composable
fun BottomNav(bottomController: NavHostController) {
    val items = listOf(
        BottomScreen.FirstScreen,
        BottomScreen.TwoScreen,
        BottomScreen.ThreeScreen,
        BottomScreen.FourScreen
    )
    val backStackEntry = bottomController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    BottomNavigation(backgroundColor = Color.Transparent) {
        items.forEach { items ->
            BottomNavigationItem(
                selected = currentRoute == items.route,
                onClick = {
                          bottomController.navigate(items.route) {
                              bottomController.graph.startDestinationRoute?.let { route ->
                                  popUpTo(route) {
                                      saveState = true
                                  }
                              }
                              restoreState = true
                              launchSingleTop = true
                          }
                },
                icon = { Icon(painter = painterResource(id = items.image), contentDescription = items.title)},
                alwaysShowLabel = true,
                label = { Text(text = items.title)},
                selectedContentColor = Orange,
            )
        }
    }
}