package com.conamobile.tarvuz.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.conamobile.tarvuz.ui.nav.Screen
import com.conamobile.tarvuz.util.MySharedPreferences

@Composable
fun SplashScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val sharedPreferences = MySharedPreferences(context)

    LaunchedEffect(true) {
        if (sharedPreferences.getFirstLogin()) {
            navController.navigate(Screen.WelcomeScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(Screen.MainScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}