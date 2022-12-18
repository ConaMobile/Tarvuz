package com.conamobile.tarvuz.app

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conamobile.tarvuz.R
import com.conamobile.tarvuz.ui.theme.DefaultBlack
import com.conamobile.tarvuz.ui.theme.DefaultWhite
import com.conamobile.tarvuz.ui.theme.TarvuzTheme
import com.conamobile.tarvuz.util.NetworkHelper
import com.conamobile.tarvuz.util.broadcast.CheckInternetReceiver
import com.conamobile.tarvuz.util.custom.CustomSpacer
import com.conamobile.tarvuz.util.custom.ParentView

class NetworkActivity : ComponentActivity() {
    private lateinit var myCheckInternetReceiver: CheckInternetReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myCheckInternetReceiver = CheckInternetReceiver()
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myCheckInternetReceiver, intent)
        setContent {
            TarvuzTheme {
                ParentView {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = painterResource(id = R.drawable.ic_network),
                            contentDescription = null,
                            tint = if (isSystemInDarkTheme()) DefaultWhite else DefaultBlack)
                        CustomSpacer(height = 20.dp)
                        Text(text = "Internet yo'q", fontSize = 25.sp)
                        CustomSpacer(height = 20.dp)
                        Text(text = "Internetsiz ilovani ishlatish imkoniyati yo'q, iltmos internetingizni yoqing!",
                            textAlign = TextAlign.Center)
                        CustomSpacer(height = 20.dp)
                        Button(onClick = { startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) }) {
                            Text(text = "Internet sozlamalari")
                        }
                        CustomSpacer(height = 10.dp)
                        Button(onClick = {
                            if (NetworkHelper(this@NetworkActivity).isNetworkConnected()) {
                                finish()
                            }
                        }) {
                            Text(text = "Tekshirish")
                        }
                    }
                }
            }
            myCheckInternetReceiver.actionChangeInternet = {
                if (it) {
                    unregisterReceiver(myCheckInternetReceiver)
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                unregisterReceiver(myCheckInternetReceiver)
                finishAffinity()
            }
        })
    }
}