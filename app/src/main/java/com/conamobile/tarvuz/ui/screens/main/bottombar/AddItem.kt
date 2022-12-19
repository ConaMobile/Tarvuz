package com.conamobile.tarvuz.ui.screens.main.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        icon = {
            Image(painter = painterResource(id = if (selected) screen.icon else screen.unselectedIcon),
                contentDescription = "Navigation Icon")
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
//                    saveState = true
                }
                launchSingleTop = true
//                restoreState = true
            }
        },
        label = {
            Text(text = screen.title)
        },
//        modifier = Modifier.background(Color.DarkGray)
    )
}