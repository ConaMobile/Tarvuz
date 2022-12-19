package com.conamobile.tarvuz.ui.screens.main.drawer

import androidx.compose.runtime.Composable
import com.conamobile.tarvuz.R

@Composable
fun prepareNavigationDrawerItems(): ArrayList<NavigationDrawerItem> {
    val itemsList = arrayListOf<NavigationDrawerItem>()

    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_home,
            label = "Bosh sahida"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_favorites,
            label = "Saqlab olinganlar",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_location,
            label = "Joylashuvni tanlash",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_post,
            label = "Mening e'lonlarim"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_ads,
            label = "Reklama berish"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_notification,
            label = "Bildirishnomalar"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_settings,
            label = "Sozlamalar"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_info,
            label = "Biz haqimizda"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_log_out,
            label = "Akkauntdan chiqish"
        )
    )

    return itemsList
}