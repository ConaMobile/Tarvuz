package com.conamobile.tarvuz.ui.screens.main.mvvm.model

import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.staggered.ListItemData

data class Posts(
    val photo: String,
    val name: String,
    val price: String,
    val time: String,
    val location: String,
    val condition: Boolean,
) : ListItemData(Type.NORMAL)