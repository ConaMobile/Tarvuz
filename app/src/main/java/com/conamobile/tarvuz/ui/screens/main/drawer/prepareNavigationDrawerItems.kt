package com.conamobile.tarvuz.ui.screens.main.drawer

import androidx.compose.runtime.Composable
import com.conamobile.tarvuz.R

@Composable
fun prepareNavigationDrawerItems(): ArrayList<NavigationDrawerItem> {
    val itemsList = arrayListOf<NavigationDrawerItem>()

    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.flag_uzbekistan,
            label = "Bosh sahida"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_location,
            label = "Saqlab olinganlar",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Joylashuvni tanlash",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Mening e'lonlarim"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Hisobingiz"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Reklama berish"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Bildirishnomalar"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Sozlamalar"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Biz haqimizda"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = R.drawable.ic_success,
            label = "Akkauntdan chiqish"
        )
    )

    return itemsList
}