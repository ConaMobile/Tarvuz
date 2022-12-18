package com.conamobile.tarvuz

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.conamobile.tarvuz.app.NetworkActivity
import com.conamobile.tarvuz.ui.nav.Navigation
import com.conamobile.tarvuz.ui.theme.TarvuzTheme
import com.conamobile.tarvuz.util.broadcast.CheckInternetReceiver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var myCheckInternetReceiver: CheckInternetReceiver

    override fun onStart() {
        super.onStart()
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myCheckInternetReceiver, intent)
        myCheckInternetReceiver.actionChangeInternet = {
            if (!it) {
                unregisterReceiver(myCheckInternetReceiver)
                startActivity(Intent(this, NetworkActivity::class.java))
            }
        }
    }

    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        myCheckInternetReceiver = CheckInternetReceiver()
        setContent {
            val navController = rememberNavController()
            TarvuzTheme {
                Surface(
                    Modifier
                        .fillMaxSize()
                        .imePadding()
                        .systemBarsPadding(),
                ) {
                    Navigation(navController, this)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TarvuzTheme {
//        Navigation(Activity())
    }
}