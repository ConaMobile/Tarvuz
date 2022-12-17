package com.conamobile.tarvuz.ui.nav

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.conamobile.tarvuz.ui.screens.home.HomeScreen
import com.conamobile.tarvuz.ui.screens.login.name.NameScreen
import com.conamobile.tarvuz.ui.screens.login.permission.PermissionScreen
import com.conamobile.tarvuz.ui.screens.login.phone_number.PhoneNumberScreen
import com.conamobile.tarvuz.ui.screens.login.sms_code.SmsCodeScreen
import com.conamobile.tarvuz.ui.screens.login.welcome.WelcomeScreen
import com.conamobile.tarvuz.ui.screens.splash.SplashScreen
import com.fragula2.compose.FragulaNavHost
import com.fragula2.compose.swipeable
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(activity: Activity) {
    val navController = rememberNavController()
    FragulaNavHost(navController = navController,
        startDestination = Screen.SplashScreen.route,
        dimAmount = 0.3f,
        parallaxFactor = 1.5f,
        animDurationMs = 400) {
        swipeable(
            route = Screen.SplashScreen.route,
        ) {
            SplashScreen(navController = navController)
        }
        swipeable(
            route = Screen.HomeScreen.route,
        ) {
            HomeScreen(navController = navController)
        }
        swipeable(
            route = Screen.WelcomeScreen.route,
        ) {
            WelcomeScreen(navController = navController)
        }
        swipeable(
            route = Screen.PhoneNumberScreen.route,
        ) {
            PhoneNumberScreen(navController = navController, activity)
        }
        swipeable(
            route = "${Screen.SmsCodeScreen.route}/{phoneNumber}",
        ) {
            val item = it.arguments?.getString("phoneNumber")
            SmsCodeScreen(navController = navController, phoneNumber = item!!, activity = activity)
        }
        swipeable(
            route = Screen.NameScreen.route,
        ) {
            NameScreen(navController = navController)
        }
        swipeable(
            route = Screen.PermissionScreen.route,
        ) {
            PermissionScreen()
        }
    }
}