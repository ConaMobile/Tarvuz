package com.conamobile.tarvuz.ui.nav

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object MainScreen : Screen("main_screen")
    object WelcomeScreen : Screen("welcome_screen")
    object PhoneNumberScreen : Screen("phone_number_screen")
    object SmsCodeScreen : Screen("sms_code_screen")
    object NameScreen : Screen("name_screen")
    object PermissionScreen : Screen("permission_screen")
    object NetworkScreen : Screen("network_screen")
}
