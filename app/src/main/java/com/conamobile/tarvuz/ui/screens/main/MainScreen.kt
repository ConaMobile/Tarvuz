package com.conamobile.tarvuz.ui.screens.main

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.conamobile.tarvuz.ui.screens.main.drawer.DrawerContent
import com.conamobile.tarvuz.ui.screens.main.drawer.MyTopAppBar
import com.conamobile.tarvuz.util.MySharedPreferences
import com.conamobile.tarvuz.util.custom.ParentView
import com.google.accompanist.insets.ui.Scaffold
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    ParentView {

        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current

        Scaffold(modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                MyTopAppBar {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            },
            drawerContent = {
                DrawerContent(sharedPreferences = MySharedPreferences(context)) { itemLabel ->
                    Toast.makeText(context, itemLabel, Toast.LENGTH_SHORT).show()
                    coroutineScope.launch {
                        // delay for the ripple effect
                        delay(timeMillis = 250)
                        scaffoldState.drawerState.close()
                    }
                }
            },
            drawerShape = AbsoluteRoundedCornerShape(topRightPercent = 15,
                bottomRightPercent = 15)) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Rest of the App UI Here")
            }
        }
    }
}