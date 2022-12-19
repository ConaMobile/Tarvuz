package com.conamobile.tarvuz.ui.screens.main.bottom_screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.conamobile.tarvuz.util.custom.ParentView

@Composable
fun FavoriteScreen() {
    ParentView {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(text = "Favorite Screen")
        }
    }
}