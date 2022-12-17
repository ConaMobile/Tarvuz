package com.conamobile.tarvuz.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.conamobile.tarvuz.ui.nav.Screen
import com.conamobile.tarvuz.util.MySharedPreferences

@Composable
fun SplashScreen(navController: NavController) {
    val sharedPreferences = MySharedPreferences(LocalContext.current)
    if (sharedPreferences.getFirstLogin()) {
        navController.navigate(Screen.WelcomeScreen.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    } else {
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}