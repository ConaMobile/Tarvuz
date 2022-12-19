package com.conamobile.tarvuz.ui.screens.main.drawer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.conamobile.tarvuz.ui.theme.DefaultGreen

@Composable
fun MyTopAppBar(onNavIconClick: () -> Unit) {
    Card(shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp),
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp), backgroundColor = DefaultGreen) {

    }
}