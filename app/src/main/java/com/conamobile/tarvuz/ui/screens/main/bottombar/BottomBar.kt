package com.conamobile.tarvuz.ui.screens.main.bottombar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.conamobile.tarvuz.ui.theme.DefaultGreen

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Favorites,
        BottomBarScreen.UploadPost,
        BottomBarScreen.Chat,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Card(shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        elevation = 0.dp) {
        BottomNavigation(elevation = 0.dp, backgroundColor = DefaultGreen) {
            screens.forEach { screen ->
                AddItem(screen = screen,
                    currentDestination = currentDestination,
                    navController = navController)
            }
        }
    }
}