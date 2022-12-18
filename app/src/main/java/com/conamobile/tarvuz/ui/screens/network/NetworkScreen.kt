package com.conamobile.tarvuz.ui.screens.network

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.conamobile.tarvuz.util.MySharedPreferences
import com.conamobile.tarvuz.util.broadcast.CheckInternetReceiver
import com.conamobile.tarvuz.util.custom.CustomSpacer
import com.conamobile.tarvuz.util.custom.ParentView

@Composable
fun NetworkScreen(
    activity: Activity,
    navController: NavController,
) {
    val context = LocalContext.current
    val sharedPreferences = MySharedPreferences(context)
    navController.enableOnBackPressed(false)
    ParentView(modifier = Modifier
        .fillMaxSize()) {
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Internet yo'q", fontSize = 30.sp)
            CustomSpacer(height = 20.dp)
            Button(onClick = {
                activity.startActivity(Intent(android.provider.Settings.ACTION_WIFI_SETTINGS))
            }) {
                Text(text = "Internet Sozlamalari")
            }
        }
    }
}