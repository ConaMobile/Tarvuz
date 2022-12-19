package com.conamobile.tarvuz.ui.screens.main.bottombar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.chat.ChatScreen
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.favorites.FavoriteScreen
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.HomeScreen
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.settings.SettingsScreen
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.upload.UploadPostScreen


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoriteScreen()
        }
        composable(route = BottomBarScreen.UploadPost.route) {
            UploadPostScreen()
        }
        composable(route = BottomBarScreen.Chat.route) {
            ChatScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}