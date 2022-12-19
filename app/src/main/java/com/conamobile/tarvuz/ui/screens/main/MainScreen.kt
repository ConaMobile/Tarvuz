package com.conamobile.tarvuz.ui.screens.main

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.conamobile.tarvuz.ui.screens.main.bottombar.BottomBar
import com.conamobile.tarvuz.ui.screens.main.bottombar.BottomNavGraph
import com.conamobile.tarvuz.ui.screens.main.drawer.DrawerContent
import com.conamobile.tarvuz.ui.screens.main.drawer.MyTopAppBar
import com.conamobile.tarvuz.ui.screens.main.mvvm.vm.MainViewModel
import com.conamobile.tarvuz.util.MySharedPreferences
import com.conamobile.tarvuz.util.custom.ParentView
import com.google.accompanist.insets.ui.Scaffold
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val bottomNavController = rememberNavController()
    val viewModel = hiltViewModel<MainViewModel>()
    val navigationBars = remember { mutableStateOf(true) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                navigationBars.value = available.y.toDouble() >= 0
                return super.onPreScroll(available, source)
            }
        }
    }

    ParentView {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
            scaffoldState = scaffoldState,
            topBar = {
                AnimatedVisibility(
                    visible = navigationBars.value,
                    enter = slideIn(tween(500, easing = FastOutSlowInEasing)) {
                        IntOffset(0, -180)
                    },
                    exit = slideOut(tween(500, easing = FastOutSlowInEasing)) {
                        IntOffset(0, -180)
                    }
                ) {
                    MyTopAppBar {}
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
            drawerShape = AbsoluteRoundedCornerShape(topRightPercent = 15, bottomRightPercent = 15),
            bottomBar = {
                AnimatedVisibility(visible = navigationBars.value,
                    enter = slideIn(tween(500, easing = FastOutSlowInEasing)) {
                        IntOffset(0, 180)
                    },
                    exit = slideOut(tween(500, easing = FastOutSlowInEasing)) {
                        IntOffset(0, 180)
                    }) {
                    BottomBar(navController = bottomNavController)
                }
            }) {
            BottomNavGraph(navController = bottomNavController)
        }
    }
}
//                        scaffoldState.drawerState.open()