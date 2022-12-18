package com.conamobile.tarvuz.ui.screens.main.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conamobile.tarvuz.BuildConfig
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.theme.DefaultGreen
import com.conamobile.tarvuz.ui.theme.DefaultYellow80
import com.conamobile.tarvuz.util.MySharedPreferences
import com.conamobile.tarvuz.util.custom.CustomSpacer

@Composable
fun DrawerContent(
    gradientColors: List<Color> = listOf(DefaultGreen, DefaultYellow80),
    sharedPreferences: MySharedPreferences,
    itemClick: (String) -> Unit,
) {

    val itemsList = prepareNavigationDrawerItems()

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(brush = Brush.verticalGradient(colors = gradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)) {

        item {

            // user's image
            Image(modifier = Modifier.size(size = 100.dp),
                painter = painterResource(id = R.drawable.ic_watermelon_1),
                contentDescription = null)

            // user's name
            Text(modifier = Modifier.padding(top = 12.dp),
                text = sharedPreferences.getUserName() ?: "Loading...",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)

            // user's email
            Text(modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                text = sharedPreferences.getUserPhone() ?: "Loading...",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White)
        }

        items(itemsList) { item ->
            NavigationListItem(item = item) {
                itemClick(item.label)
            }
        }

        item {
            CustomSpacer(height = 30.dp)
            Text(text = "versiya: " + BuildConfig.VERSION_NAME)
        }
    }
}