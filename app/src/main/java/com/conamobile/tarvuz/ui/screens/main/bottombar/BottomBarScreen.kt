package com.conamobile.tarvuz.ui.screens.main.bottombar

import androidx.annotation.DrawableRes
import com.conamobile.tarvuz.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val unselectedIcon: Int,
) {
    object Home : BottomBarScreen(route = "home_screen",
        title = "Bosh Sahifa",
        icon = R.drawable.ic_home,
        unselectedIcon = R.drawable.ic_location)

    object Favorites : BottomBarScreen(route = "favorites_screen",
        title = "Saqlanganlar",
        icon = R.drawable.ic_location,
        unselectedIcon = R.drawable.ic_location)

    object UploadPost : BottomBarScreen(route = "upload_post_screen",
        title = "E'lon yuklash",
        icon = R.drawable.ic_location,
        unselectedIcon = R.drawable.ic_location)

    object Chat : BottomBarScreen(route = "chat_screen",
        title = "Chat",
        icon = R.drawable.ic_location,
        unselectedIcon = R.drawable.ic_location)

    object Settings : BottomBarScreen(route = "settings_screen",
        title = "Sozlamalar",
        icon = R.drawable.ic_location,
        unselectedIcon = R.drawable.ic_location)
}